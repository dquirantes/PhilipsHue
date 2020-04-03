import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ClienteHue {

	public static void main(String[] args) {
		try
		{

			URL url = new URL ("http://192.168.1.40/api/pRsDhAIMLcIUfNI8iWM1AJtWGCg7BsRU19AFeSfk/lights/2/state");		
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("PUT");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);

			String jsonInputString = "{\"on\":true}";
			try(OutputStream os = con.getOutputStream()) {
				byte[] input = jsonInputString.getBytes("utf-8");
				os.write(input, 0, input.length);           
			}

			try(BufferedReader br = new BufferedReader(
					new InputStreamReader(con.getInputStream(), "utf-8"))) {
				StringBuilder response = new StringBuilder();
				String responseLine = null;
				while ((responseLine = br.readLine()) != null) {
					response.append(responseLine.trim());
				}
				System.out.println(response.toString());
			}


		}
		catch (Exception e)
		{
			e.printStackTrace();
		}


	}

}
