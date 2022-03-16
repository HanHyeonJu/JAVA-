package com.example.todo.retrofit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public static UserService getUserService() {
        UserService userService = getInstance().create(UserService.class);
        return userService;
    }

//    public static TodoService getTodoService() {
//        return null;
//    }

    private static Retrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.100.202.85:8090/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;
    } // getInstance

}
