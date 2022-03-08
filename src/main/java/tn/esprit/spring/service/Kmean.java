package tn.esprit.spring.service;

public class Kmean {
	public interface LastFmService {

	    @GET("/2.0/?method=chart.gettopartists&format=json&limit=50")
	    Call<Artists> topArtists(@Query("page") int page);

	    @GET("/2.0/?method=artist.gettoptags&format=json&limit=20&autocorrect=1")
	    Call<Tags> topTagsFor(@Query("artist") String artist);

	    @GET("/2.0/?method=chart.gettoptags&format=json&limit=100")
	    Call<TopTags> topTags();

	    // A few DTOs and one interceptor
	}
	
	
	
	private static List<String> getTop100Artists() throws IOException {
	    List<String> artists = new ArrayList<>();
	    // Fetching the first two pages, each containing 50 records.
	    for (int i = 1; i <= 2; i++) {
	        artists.addAll(lastFm.topArtists(i).execute().body().all());
	    }

	    return artists;
	}
	
	private static Set<String> getTop100Tags() throws IOException {
	    return lastFm.topTags().execute().body().all();
	}
	
	
	private static List<Record> datasetWithTaggedArtists(List<String> artists, 
			  Set<String> topTags) throws IOException {
			    List<Record> records = new ArrayList<>();
			    for (String artist : artists) {
			        Map<String, Double> tags = lastFm.topTagsFor(artist).execute().body().all();
			            
			        // Only keep popular tags.
			        tags.entrySet().removeIf(e -> !topTags.contains(e.getKey()));

			        records.add(new Record(artist, tags));
			    }

			    return records;
			}
	
	List<String> artists = getTop100Artists();
	Set<String> topTags = getTop100Tags();
	List<Record> records = datasetWithTaggedArtists(artists, topTags);

	Map<Centroid, List<Record>> clusters = KMeans.fit(records, 7, new EuclideanDistance(), 1000);
	// Printing the cluster configuration
	clusters.forEach((key, value) -> {
	    System.out.println("-------------------------- CLUSTER ----------------------------");

	    // Sorting the coordinates to see the most significant tags first.
	    System.out.println(sortedCentroid(key)); 
	    String members = String.join(", ", value.stream().map(Record::getDescription).collect(toSet()));
	    System.out.print(members);

	    System.out.println();
	    System.out.println();
	});
}



