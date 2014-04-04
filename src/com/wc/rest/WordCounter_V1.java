package com.wc.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jettison.json.JSONObject;

import com.wc.io.MultipleFileReader;

/**
 * This class is used for word Counter API. The rest functionality is managed by
 * this class which obtains the query param from the Http request and responds
 * back with the count value after processing the request behind scenes
 * 
 * http://localhost:8080/Counter/api/v1/wordcounter?query=TEST
 * 
 * @author Smruthi A
 */
@Path("/v1/wordcounter")
public class WordCounter_V1 {
	private static final String api_version = "01.00.00"; // version of the api


	/**
	 * This method will return the version number of the api Note: this is
	 * nested one down from the root. You will need to add version into the URL
	 * path.
	 * 
	 * Example: http://localhost:7001/com.youtube.rest/api/v1/status/version
	 * 
	 * @return String - version number of the api
	 */
	@Path("/version")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String returnVersion() {
		return "<p>Version:</p>" + api_version;
	}

	/**
	 * This method will return the specific keyword count the user is looking
	 * for. It uses a QueryParam bring in the data to the method.
	 * 
	 * Example would be:
	 * http://localhost:8080/Counter/api/v1/wordcounter?query=TEST
	 * 
	 * @param keyword
	 *            - word
	 * @return - json array results list from the data parsed from multiple
	 *         files
	 * @throws Exception
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response returnkeywordParts(@QueryParam("query") String keyword)
			throws Exception {

		String returnString = null;
		JSONObject json = new JSONObject();
		System.out.println("KEYWORD:: " + keyword);
		try {

			// return a error is keyword is missing from the url string
			if (keyword == null) {
				return Response
						.status(400)
						.entity("Error: please specify correct QuryParam (query) for this search")
						.build();
			}

			MultipleFileReader multipleFileReader = new MultipleFileReader();
			json = multipleFileReader.getCount(keyword.toLowerCase()); // fetch the json object of the count and the keyword
			returnString = json.toString(); // display the output as json

		} catch (Exception e) {
			e.printStackTrace();
			return Response.status(500)
					.entity("Server was not able to process your request")
					.build();
		}

		return Response.ok(returnString).build();
	}

}