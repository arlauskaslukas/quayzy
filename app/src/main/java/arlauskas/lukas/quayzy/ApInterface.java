package arlauskas.lukas.quayzy;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApInterface {
    @GET("register.php")
    Call<User> performRegistration(@Query("email") String Email, @Query("username") String Username,
                                   @Query("password") String Password);


    @GET("login.php")
    Call<User> performUserLogin(@Query("username") String Username, @Query("password") String Password);

    @GET("question.php")
    Call<Question> retrieveQuestion(@Query("id") int id);

    @GET("retrievebest.php")
    Call<ArrayList<UserScores>> retrieveBest();

    @GET("retrievescore.php")
    Call<UserScores> retrieveScore(@Query("username") String name);

    @POST("setscore.php")
    Call<String> setScore(@Query("score") int score, @Query("username") String name);
}
