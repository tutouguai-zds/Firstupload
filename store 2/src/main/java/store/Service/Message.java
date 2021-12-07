package store.Service;


import lombok.Data;
@Data
//给前端返回的消息类，包含了message消息，对象，和状态玛
public class Message {
	
	String message;
	Integer statuscode;
	Object object;

}
