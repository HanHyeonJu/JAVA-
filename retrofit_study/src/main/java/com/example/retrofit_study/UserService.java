package com.example.retrofit_study;

import com.example.domain.User;
import com.example.domain.UserListResult;
import com.example.domain.UserOneResult;
import com.example.domain.UserResult;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

// Retrofit 객체의 create메소드에 의해 자동으로 구현 객체를 만들 수 있음.
// 구현된 서비스 객체의 용도는 GET, POST, PUT, DELETE 요청을 담당하는 메소드를 가짐
public interface UserService {
	
	@GET("TODO/api/user/")
	Call<UserOneResult> getUser( // 변수가 3~4개 까지는 각각해줘도 괜찮음
			@Query("category") String category, 
			@Query("userName") String userName); // ?category = String category & userName = String userName
	// @QueryMap Map<String, String> options => 이렇게 하면 각각 query string을 주지 않아도 됨
	
	@GET("TODO/api/user/")
	Call<UserListResult> getUsers(@Query("category") String category);
	
	@POST("TODO/api/user/")
	Call<UserResult> createUser(@Body User user);
	
	@PUT("TODO/api/user/")
	Call<UserResult> updateUser(@Body User user);
	
	@DELETE("TODO/api/user/")
	Call<UserResult> deleteUser(@Query("userName") String userName);
}
