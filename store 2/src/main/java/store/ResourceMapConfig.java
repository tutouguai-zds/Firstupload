package store;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//资源映射类
public class ResourceMapConfig implements WebMvcConfigurer {
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry reg) {
		//对/headimg/下资源的访问映射到对D:/upload/下
		reg.addResourceHandler("/headimg/**").addResourceLocations("file:D:/upload/");
		reg.addResourceHandler("/goodsimg/**").addResourceLocations("file:D:/goodsimage/");
	}
}
