package store.Security;




import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.cors.CorsConfiguration;



@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsService uds;
	
	//匿名用户处理
	@Autowired
	AuthenticationEntryPoint aep;
	
	//登录成功处理
	@Autowired
	SuccessfulAuthenticationSuccessHandler sfa;
	
	//登录失败处理
	@Autowired
	FailuredAuthenticaltionFailuredHandler faf;
	
	//退出登录处理
	@Autowired
	Logout logout;
	//密码转码器
	@Bean
	public PasswordEncoder encoder() {
		return new StandardPasswordEncoder();
	}
	//配置处理允许跨域请求的映射路径
	@Bean
	public org.springframework.web.filter.CorsFilter corsFilter() {
		org.springframework.web.cors.UrlBasedCorsConfigurationSource source= new org.springframework.web.cors.UrlBasedCorsConfigurationSource();
		CorsConfiguration config=new CorsConfiguration();
		List<String> OriginPatterns=new ArrayList<>();
		OriginPatterns.add("*");
		config.setAllowedOriginPatterns(OriginPatterns);
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return new org.springframework.web.filter.CorsFilter(source);
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//使用自定义权限服务
		auth.userDetailsService(uds);
	}
	@Override
	protected void configure(HttpSecurity auth) throws Exception {
		auth.csrf().disable()
		//配置资源访问权限
		.authorizeRequests()
		.antMatchers("/api/orders","/api/users","/api/roles").hasRole("ADMIN")
		.antMatchers("/api/goodses","/api").hasAnyRole("USER","ADMIN")
		.antMatchers("/headimg/**").permitAll()
		.anyRequest().permitAll()
		
		//登录相关处理
		.and()
		//开启登录功能
		.formLogin()
		//登录处理url，,security框架会拦截到此url的请求并处理
		.loginProcessingUrl("/login")
		.permitAll()
		//成功后的操作
		.successHandler(sfa)
		//失败后的操作
		.failureHandler(faf)
		
		//匿名登录处理
		.and()
		.exceptionHandling().authenticationEntryPoint(aep)
		
		//登出处理
		.and()
		.logout()
		.permitAll()
		//登出成功后的操作
		.logoutSuccessHandler(logout)
		//清除session相关的信息
		.deleteCookies("JSESSIONID")
		
		//开启跨域请求的相应配置，使之生效
		.and()
		.cors();
	}
	
}
