package com.example.todo.retrofit;


import com.example.todo.domain.User;
import com.example.todo.domain.UserListResult;
import com.example.todo.domain.UserOneResult;
import com.example.todo.domain.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

// Retrofit 객체의 create 메소드에 의해 자동으로 구현객체를 만들수 있음.
// 구현된 서비스 객체의 용도는 GET, POST, PUT, DELETE 요청을 담당하는
// 메소드를 가짐.
public interface UserService {

	@GET("TODO/api/user/")
	Call<UserOneResult> getUser(
			@Query("category") String category, 
			@Query("userName") String userName);
	
//	@GET("TODO/api/user/")
//	Call<UserOneResult> getUser(@QueryMap Map<String, String> options);
	
	@GET("TODO/api/user/")
	Call<UserListResult> getUsers(@Query("category") String category);
	
	@POST("TODO/api/user/")
	Call<UserResult> createUser(@Body User user);
	
	@PUT("TODO/api/user/")
	Call<UserResult> updateUser(@Body User user);
	
	@DELETE("TODO/api/user/")
	Call<UserResult> deleteUser(@Query("userName") String userName);
	
} // interface UserService







