package thingy2;

import static spark.Spark.get;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;

import javax.swing.JComponent;

import com.google.gson.JsonParser;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.SpotifyHttpManager;
import com.wrapper.spotify.model_objects.credentials.AuthorizationCodeCredentials;
import com.wrapper.spotify.model_objects.miscellaneous.Device;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeRequest;
import com.wrapper.spotify.requests.authorization.authorization_code.AuthorizationCodeUriRequest;
import com.wrapper.spotify.requests.data.player.GetUsersAvailableDevicesRequest;
import com.wrapper.spotify.requests.data.player.StartResumeUsersPlaybackRequest;


public class MainOne
{
	/**
	 * Angry: spotify:user:spotify:playlist:37i9dQZF1DX2LTcinqsO68 - spotify:track:2JS1iE5A5RHvUPH5Zl9jlF
	 * Disgust: spotify:user:spotify:playlist:37i9dQZF1DX4VvfRBFClxm - spotify:track:5Z1U0knfYnsfvZycgDeOiC
	 * Fear: spotify:user:spotify:playlist:37i9dQZF1DX8S9gwdi7dev - spotify:track:20efeySIfZoiSaISGLBbNs
	 * Happy: spotify:user:spotify:playlist:37i9dQZF1DWSqmBTGDYngZ - spotify:track:1aTAR21kFePsDBHnc74Hli
	 * Sad: spotify:user:spotify:playlist:37i9dQZF1DX4fpCWaHOned - spotify:track:3XIIOCu6B8PuGq5j61asEM
	 * Surprise: spotify:user:spotify:playlist:37i9dQZF1DX4UtSsGT1Sbe - spotify:track:4uLU6hMCjMI75M1A2tKUQC
	 * Neutral: spotify:user:spotify:playlist:37i9dQZF1DWSiZVO2J6WeI - spotify:track:4KyJQKAFvEH4F52qSMuwIF
	 *
	 *
	 *
	 * @param args
	 * @throws Exception
	 */
	public static void main(String args[]) throws Exception
	{


		String clientId = "ed82b3c3fef8485a956d7d27ab969f75";
		String clientSecret = "37d0d67c46984585b00dacfe06c6208b";
		String token = "AQCqgAYHyW0oaHt8aggugo9Dtk_-dGL2secu4zebHdIcUdwvdVt8S3bFE6Sys7bGG1tuLfmJKunGpXtDHLrv6u4I8-veJM1IitgEvgWufl-tqXRTSOMV3EVZqCcWiD1BRzXoSpvAlGu3xs8h2Vkloc0DyDe6_NaU6uBfhPIykUBONJfoXyHA6kpHURdWDigOHXhofGQyYgWTvOOIF8oiR4RmehIjoheU-4v0B_fkZdcGboi7nYUtwrk5P6FyUMJRPpLtpRgVYx1Al04mQJI_WzHWCRxrfMpDYMrLm245NkLAjrBL1PhntgqK-sd2BdC5DSxIlGvPN7m_TvUlwrBdR_SNHiIxC8wY5fTpqdSsAdOMNApDrhPNuZmHh0hScv3LOdF5h4Hdmqvcm-8";
		//String token = "AQCAND5rENkFegWb_OQBxTO4BU5zqFNx3upyoF8oKoG2K9SThRsr-PcumL3LLbYRHzGb2GBMBZAaaY-diBtebkDSPPqhtyt4uyH8rIpSielmmHxNhW3TRtYilxJPCIiPvdfW-1ryRLYdWsCtXtyezv0r62T3cOCTMXssrRI7b9-anHLHGwS4EmqeOZSFRoa5lYcF6t6x_aRxceDFXpTQhGTpGPkSbw_VoF84Pl0nwVHCczAuGnbHtmTVg2zacZv8IVzjh5Q0kk_DQRnvMlMQ3P_a8Cqg2N6128CO0JWik5xrxbKt_Q18fyzUyYhaYknPvZqwYwbpOWVOaqiZMtrGL02hCTpaAyXi1ZvjJOjHCsFlzpC3JULtVFR5r6ogRcaUeXL3juqGI3hRoIk";
		final URI redirectUri = SpotifyHttpManager
				.makeUri("http://localhost:8888/callback");

		/*
		 * final SpotifyApi spotifyApi = new SpotifyApi.Builder()
		 * .setClientId(clientId) .setClientSecret(clientSecret) .build(); final
		 * ClientCredentialsRequest clientCredentialsRequest =
		 * spotifyApi.clientCredentials() .build();
		 *
		 *
		 * try { final ClientCredentials clientCredentials =
		 * clientCredentialsRequest.execute();
		 *
		 * // ...
		 *
		 * //final ClientCredentials clientCredentials =
		 * clientCredentialsFuture.get();
		 *
		 * // Set access token for further "spotifyApi" object usage
		 * spotifyApi.setAccessToken(clientCredentials.getAccessToken());
		 *
		 * System.out.println("Expires in: " +
		 * clientCredentials.getExpiresIn()); } catch (Exception e) {
		 * System.out.println("Error: " + e.getCause().getMessage()); }
		 */
		final SpotifyApi spotifyApi = new SpotifyApi.Builder()//.setAccessToken(token)
				.setClientId(clientId).setClientSecret(clientSecret).setRedirectUri(redirectUri)
				.build();
		final AuthorizationCodeUriRequest authorizationCodeUriRequest = spotifyApi
				.authorizationCodeUri().state("x4xkmn9pu3j6ukrs8n")
				.scope("user-read-private,user-read-birthdate,user-read-email,streaming,user-modify-playback-state,user-read-currently-playing,user-read-playback-state,user-library-read,user-library-modify,playlist-read-private,playlist-modify-public,playlist-modify-private,playlist-read-collaborative").show_dialog(true)
				.build();
		final URI uri = authorizationCodeUriRequest.execute();
		java.awt.Desktop.getDesktop().browse(uri);
		Scanner s = new Scanner(System.in);
		System.out.print("URL is: ");
		final AuthorizationCodeRequest authorizationCodeRequest = spotifyApi
				.authorizationCode(s.nextLine().split("=")[1].split("&")[0]).build();


		final AuthorizationCodeCredentials authorizationCodeCredentials = authorizationCodeRequest
				.execute();

		// Set access and refresh token for further "spotifyApi" object usage
		spotifyApi
				.setAccessToken(authorizationCodeCredentials.getAccessToken());
		spotifyApi.setRefreshToken(authorizationCodeCredentials
				.getRefreshToken());

		System.out.println("Expires in: "
				+ authorizationCodeCredentials.getExpiresIn());






		/*final GetPlaylistRequest getPlaylistRequest = spotifyApi.getPlaylist(
				"whatchasayyyyy", "3DUJJeDiDtzGp3WqK3V99z").build();

		Playlist play = getPlaylistRequest.execute();
		Image img = play.getImages()[0];
		BufferedImage image = null;
		try
		{
			URL url = new URL(img.getUrl());
			image = ImageIO.read(url);
		}
		catch (IOException e)
		{
		}
		System.out.println(play.getImages()[0].getUrl());*/

		/*JFrame frame = new JFrame();
		frame.setSize(1200, 750);

		JEditorPane jep = new JEditorPane();

		frame.add(new ImageComponent(image));
		frame.setVisible(true);*/

		final GetUsersAvailableDevicesRequest getUsersAvailableDevicesRequest = spotifyApi
				.getUsersAvailableDevices().build();
		final Device[] devices = getUsersAvailableDevicesRequest.execute();

		/*final CreatePlaylistRequest createPlaylistRequest = spotifyApi.createPlaylist("whatchasayyyyy", "BEST_PLAYLIST")
		          .collaborative(false)
		          .public_(false)
		          .description("Amazing music.")
		          .build();
		Playlist created = createPlaylistRequest.execute();

		final String[] uris = new String[]{"spotify:track:1ZHYJ2Wwgxes4m8Ba88PeK","spotify:track:3djNBlI7xOggg7pnsOLaNm"};

		final AddTracksToPlaylistRequest addTracksToPlaylistRequest = spotifyApi
		          .addTracksToPlaylist("whatchasayyyyy", created.getId(), uris)
		          .position(0)
		          .build();
		addTracksToPlaylistRequest.execute();*/

		/*Thread.sleep(2000);
		final GetPlaylistsTracksRequest getPlaylistsTracksRequest = spotifyApi
		          .getPlaylistsTracks("whatchasayyyyy", created.getId())
		          .fields("description")
		          .limit(10)
		          //.offset(0)
		          //.market(CountryCode.SE)
		          .build();
		Paging<PlaylistTrack> pL = getPlaylistsTracksRequest.execute();
		System.out.println(pL.getTotal());*/

		/*final PauseUsersPlaybackRequest pauseUsersPlaybackRequest = spotifyApi.pauseUsersPlayback()
		          .device_id(devices[0].getId())
		          .build();
		pauseUsersPlaybackRequest.execute();*/
		//Thread.sleep(1000);
		//pauseUsersPlaybackRequest.execute();
		/*final SeekToPositionInCurrentlyPlayingTrackRequest seekToPositionInCurrentlyPlayingTrackRequest =
		          spotifyApi.seekToPositionInCurrentlyPlayingTrack(38000)
		                  .device_id(devices[0].getId())
		                  .build();
		seekToPositionInCurrentlyPlayingTrackRequest.execute();
		while(true)
		{
			Thread.sleep(500);
			seekToPositionInCurrentlyPlayingTrackRequest.execute();
		}*/
		/*while(true)
		{
			final SetVolumeForUsersPlaybackRequest setVolumeForUsersPlaybackRequest = spotifyApi
			          .setVolumeForUsersPlayback((int)((Math.sin(System.currentTimeMillis()*2*Math.PI/10000.0)+1)/2.0*50+50))
			          .device_id(devices[0].getId())
			          .build();
			setVolumeForUsersPlaybackRequest.execute();
			Thread.sleep(20);
		}*/

		//System.out.println(devices[0].getVolume_percent());

		/*
		 * final GetAlbumRequest getAlbumRequest =
		 * spotifyApi.getAlbum("1P2L7DD1DCqb0VfpMyByLl") .market(CountryCode.US)
		 * .build(); final Album album = getAlbumRequest.execute();
		 *
		 * System.out.println("Name: " + album.getCopyrights()[2].getText());
		 */

		/*
		 * SpotifyApi spotifyApi = new SpotifyApi.Builder() .setAccessToken(
		 * "taHZ2SdB-bPA3FsK3D7ZN5npZS47cMy-IEySVEGttOhXmqaVAIo0ESvTCLjLBifhHOHOIuhFUKPW1WMDP7w6dj3MAZdWT8CLI2MkZaXbYLTeoDvXesf2eeiLYPBGdx8tIwQJKgV8XdnzH_DONk"
		 * ) .build(); System.out.println(spotifyApi);
		 */

		HashMap<String,String> emotionToSong = new HashMap<String,String>();
		HashMap<String,String> emotionToPlaylist = new HashMap<String,String>();

		emotionToSong.put("angry", "spotify:track:2JS1iE5A5RHvUPH5Zl9jlF");
		emotionToSong.put("disgust","spotify:track:5Z1U0knfYnsfvZycgDeOiC");
		emotionToSong.put("fear", "spotify:track:20efeySIfZoiSaISGLBbNs");
		emotionToSong.put("happy", "spotify:track:1aTAR21kFePsDBHnc74Hli");
		emotionToSong.put("sad", "spotify:track:6PUIzlqotEmPuBfjbwYWOB");
		emotionToSong.put("surprise", "spotify:track:4uLU6hMCjMI75M1A2tKUQC");
		emotionToSong.put("neutral","spotify:track:4KyJQKAFvEH4F52qSMuwIF");

		emotionToPlaylist.put("angry", "spotify:user:spotify:playlist:37i9dQZF1DX2LTcinqsO68");
		emotionToPlaylist.put("disgust","spotify:user:spotify:playlist:37i9dQZF1DX4VvfRBFClxm");
		emotionToPlaylist.put("fear", "spotify:user:spotify:playlist:37i9dQZF1DX8S9gwdi7dev");
		emotionToPlaylist.put("happy", "spotify:user:spotify:playlist:37i9dQZF1DWSqmBTGDYngZ");
		emotionToPlaylist.put("sad", "spotify:user:spotify:playlist:37i9dQZF1DX4fpCWaHOned");
		emotionToPlaylist.put("surprise", "spotify:user:spotify:playlist:37i9dQZF1DX4UtSsGT1Sbe");
		emotionToPlaylist.put("neutral","spotify:user:spotify:playlist:37i9dQZF1DWSiZVO2J6WeI");
		/* Angry: spotify:user:spotify:playlist:37i9dQZF1DX2LTcinqsO68 - spotify:track:2JS1iE5A5RHvUPH5Zl9jlF
		 * Disgust: spotify:user:spotify:playlist:37i9dQZF1DX4VvfRBFClxm - spotify:track:5Z1U0knfYnsfvZycgDeOiC
		 * Fear: spotify:user:spotify:playlist:37i9dQZF1DX8S9gwdi7dev - spotify:track:20efeySIfZoiSaISGLBbNs
		 * Happy: spotify:user:spotify:playlist:37i9dQZF1DWSqmBTGDYngZ - spotify:track:1aTAR21kFePsDBHnc74Hli
		 * Sad: spotify:user:spotify:playlist:37i9dQZF1DX4fpCWaHOned - spotify:track:6PUIzlqotEmPuBfjbwYWOB
		 * Surprise: spotify:user:spotify:playlist:37i9dQZF1DX4UtSsGT1Sbe - spotify:track:4uLU6hMCjMI75M1A2tKUQC
		 * Neutral: spotify:user:spotify:playlist:37i9dQZF1DWSiZVO2J6WeI - spotify:track:4KyJQKAFvEH4F52qSMuwIF
		*/
		get("/:emotion", (request, response) -> {
			String theEmotion = request.params(":emotion");
			System.out.println("Playing emotion: "+theEmotion + " @ "+new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()));
			try
			{
			final StartResumeUsersPlaybackRequest startResumeUsersPlaybackRequest = spotifyApi
			          .startResumeUsersPlayback()
			          //.context_uri(created.getUri())
			          .context_uri(emotionToPlaylist.get(theEmotion))
			          .device_id(devices[0].getId())
			          .offset(new JsonParser().parse("{\"uri\":\""+emotionToSong.get(theEmotion)+"\"}").getAsJsonObject())
			          //.uris(new JsonParser().parse("[\"spotify:track:1ZHYJ2Wwgxes4m8Ba88PeK\"]").getAsJsonArray())
			          .build();
			startResumeUsersPlaybackRequest.execute();
			}
			catch(Exception err)
			{
				err.printStackTrace();
			}
		    return "Playing emotion: " + theEmotion;
		});
	}

	static class ImageComponent extends JComponent
	{
		/**
	     *
	     */
		private static final long serialVersionUID = 1L;
		private BufferedImage image;

		public ImageComponent(BufferedImage img)
		{
			image = img;
		}

		public void paintComponent(Graphics g)
		{
			if (image == null)
				return;
			int imageWidth = image.getWidth(this);
			int imageHeight = image.getHeight(this);

			g.drawImage(image, 50, 50, this);

			/*
			 * for (int i = 0; i*imageWidth <= getWidth(); i++) for(int j = 0;
			 * j*imageHeight <= getHeight();j++) if(i+j>0) g.copyArea(0, 0,
			 * imageWidth, imageHeight, i*imageWidth, j*imageHeight);
			 */
		}

	}
}
