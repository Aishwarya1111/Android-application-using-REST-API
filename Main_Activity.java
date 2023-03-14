public class MainActivity extends AppCompatActivity{
private static final String TAG = MainActivity.class.getSimpleName();
public static final String BASE_URL = "http://api.themoviedb.org/3/";
private static Retrofit retrofit = null;
private RecyclerView recyclerView = null;
// insert your themoviedb.org API KEY here
private final static String API_KEY = "";
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
recyclerView.setHasFixedSize(true);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
connectAndGetApiData();
}
// This method create an instance of Retrofit
// set the base url
public void connectAndGetApiData(){
if (retrofit == null) {
retrofit = new Retrofit.Builder()
.baseUrl(BASE_URL)
.addConverterFactory(GsonConverterFactory.create())
.build();
}
MovieApiService movieApiService = retrofit.create(MovieApiService.class);
Call<MovieResponse> call = movieApiService.getTopRatedMovies(API_KEY);
call.enqueue(new Callback<MovieResponse>() {
@Override
public void onResponse(Call<MovieResponse>call, Response<MovieResponse> response)
{
recyclerView.setAdapter(newMoviesAdapter
(movies,R.layout.list_item_movie, getApplicationContext()));
Log.d(TAG, "Number of movies received: " + movies.size());
}
@Override
public void onFailure(Call<MovieResponse> call, Throwable throwable) {
Log.e(TAG, throwable.toString());
}
});
}
}
public class Movie {
@SerializedName("poster_path")
private String posterPath;
@SerializedName("adult")
private boolean adult;
@SerializedName("overview")
private String overview;
@SerializedName("release_date")
private String releaseDate;
@SerializedName("genre_ids")
private List<Integer> genreIds = new ArrayList<Integer>();
@SerializedName("id")
private Integer id;
@SerializedName("original_title")
private String originalTitle;
@SerializedName("original_language")
private String originalLanguage;
@SerializedName("title")
private String title;
@SerializedName("backdrop_path")
private String backdropPath;
@SerializedName("popularity")
private Double popularity;
@SerializedName("vote_count")
private Integer voteCount;
private Boolean video;
@SerializedName("vote_average")
private Double voteAverage;
public Movie(String posterPath, boolean adult, String overview, String releaseDate,
List<Integer> genreIds, Integer id,
String originalTitle, String originalLanguage, String title, String backdropPath, Double
popularity,
Integer voteCount, Boolean video, Double voteAverage) {
this.posterPath = posterPath;
this.adult = adult;
this.overview = overview;
this.releaseDate = releaseDate;
this.genreIds = genreIds;
this.id = id;
this.originalTitle = originalTitle;
this.originalLanguage = originalLanguage;
this.title = title;
this.backdropPath = backdropPath;
this.popularity = popularity;
this.voteCount = voteCount;
this.video = video;
this.voteAverage = voteAverage;
}
public String getPosterPath() {
return posterPath;
}
public void setPosterPath(String posterPath) {
this.posterPath = posterPath;
}
public boolean isAdult() {
return adult;
}
public void setAdult(boolean adult) {
this.adult = adult;
}
public String getOverview() {
}
public void setOverview(String overview) {
this.overview = overview;
}
public String getReleaseDate() {
return releaseDate;
}
public void setReleaseDate(String releaseDate) {
this.releaseDate = releaseDate;
}
public List<Integer> getGenreIds() {
return genreIds;
}
public void setGenreIds(List<Integer> genreIds) {
this.genreIds = genreIds;
}
public Integer getId() {
return id;
}
public void setId(Integer id) {
this.id = id;
}
public String getOriginalTitle() {
return originalTitle;
}
public void setOriginalTitle(String originalTitle) {
this.originalTitle = originalTitle;
}
public String getOriginalLanguage() {
return originalLanguage;
}
public void setOriginalLanguage(String originalLanguage) {
this.originalLanguage = originalLanguage;
}
public String getTitle() {
return title;
public void setTitle(String title) {
this.title = title;
}
public String getBackdropPath() {
return backdropPath;
}
public void setBackdropPath(String backdropPath) {
this.backdropPath = backdropPath;
}
public Double getPopularity() {
return popularity;
}
public void setPopularity(Double popularity) {
this.popularity = popularity;
}
public Integer getVoteCount() {
return voteCount;
}
public void setVoteCount(Integer voteCount) {
this.voteCount = voteCount;
}
public Boolean getVideo() {
return video;
}
public void setVideo(Boolean video) {
this.video = video;
}
public Double getVoteAverage() {
return voteAverage;
}
public void setVoteAverage(Double voteAverage) {
this.voteAverage = voteAverage;
}
}
public class MovieResponse {
@SerializedName("page")
@SerializedName("results")
private List<Movie> results;
@SerializedName("total_results")
private int totalResults;
@SerializedName("total_pages")
private int totalPages;
public int getPage() {
return page;
}
public void setPage(int page) {
this.page = page;
}
public List<Movie> getResults() {
return results;
}
public void setResults(List<Movie> results) {
this.results = results;
}
public int getTotalResults() {
return totalResults;
}
public void setTotalResults(int totalResults) {
this.totalResults = totalResults;
}
public int getTotalPages() {
return totalPages;
}
public void setTotalPages(int totalPages) {
this.totalPages = totalPages;
}
}
public interface MovieApiService {
@GET(“movie/top_rated”)
Call<MovieResponse> getTopRatedMovies(@Query(“api_key”) String apiKey);
}
public class MainActivity extends AppCompatActivity{
public static final String BASE_URL = "http://api.themoviedb.org/3/";
private static Retrofit retrofit = null;
private RecyclerView recyclerView = null;
// insert your themoviedb.org API KEY here
private final static String API_KEY = "";
@Override
protected void onCreate(Bundle savedInstanceState) {
super.onCreate(savedInstanceState);
setContentView(R.layout.activity_main);
recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
recyclerView.setHasFixedSize(true);
recyclerView.setLayoutManager(new LinearLayoutManager(this));
connectAndGetApiData();
}
// This method create an instance of Retrofit
// set the base url
public void connectAndGetApiData(){
if (retrofit == null) {
retrofit = new Retrofit.Builder()
.baseUrl(BASE_URL)
.addConverterFactory(GsonConverterFactory.create())
.build();
}
MovieApiService movieApiService = retrofit.create(MovieApiService.class);
Call<MovieResponse> call = movieApiService.getTopRatedMovies(API_KEY);
call.enqueue(new Callback<MovieResponse>() {
@Override
public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
List<Movie> movies = response.body().getResults();
recyclerView.setAdapter(newMoviesAdapter(movies,R.layout.list_item_movie,
getApplicationContext()));
Log.d(TAG, "Number of movies received: " + movies.size());
}
@Override
public void onFailure(Call<MovieResponse> call, Throwable throwable) {
Log.e(TAG, throwable.toString());
});
}
}
Footer