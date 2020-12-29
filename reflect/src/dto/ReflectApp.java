package dto;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectApp {
	
	static <T> void myReflect(T dto) {
		Method[] methods = dto.getClass().getMethods();
		
		for (Method method : methods) {
			System.out.println(method.getName());
		}
		
		Field[] fs = dto.getClass().getDeclaredFields();
		for(Field f : fs) {
			f.setAccessible(true);		// private 멤버에 접근 가능 기법
			try {
				
//				if (f.getName().equals("password")) {	// 패스워드
//					f.set(dto, "5678");
//				}
				Object o = f.get(dto);
				System.out.println(o);
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
	}
		
	
	
	public static void main(String[] args) {
		LoginDto loginDto = new LoginDto();
		loginDto.setUsername("irone");
		loginDto.setPassword("1234");
		
		JoinDto joinDto = new JoinDto();
		joinDto.setUsername("irone");
		joinDto.setPassword("1234");
		joinDto.setEmail("irone@gmail.com");
		
		myReflect(joinDto);
	}
}
