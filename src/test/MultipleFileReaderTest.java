/**
 * 
 */
package test;

import junit.framework.Assert;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.wc.io.MultipleFileReader;

/**
 * @author smrurao
 *
 */
public class MultipleFileReaderTest {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testforKeyword() throws JSONException {
		String keyword="test";
		
		MultipleFileReader multipleFileReader = new MultipleFileReader();
		JSONObject json = multipleFileReader.getCount(keyword.toLowerCase()); 
		Assert.assertNotSame(json.get("keyword").toString().equalsIgnoreCase(keyword),"Keyword :: "+keyword+" doesnot match the keyword returned by JSON Actual:: "+json.getString("keyword"));
		Assert.assertNotNull(json.get("count").toString(),"Count :: "+keyword+"is null");
		
	}
	@Test
	public void testforNoKeyword() throws JSONException {
		String keyword="";
		
		MultipleFileReader multipleFileReader = new MultipleFileReader();
		JSONObject json = multipleFileReader.getCount(keyword.toLowerCase()); 
		Assert.assertNotSame(json.get("keyword").toString().equalsIgnoreCase(keyword),"Keyword :: "+keyword+" doesnot match the keyword returned by JSON Actual:: "+json.getString("keyword"));
		Assert.assertNotNull(json.get("count").toString(),"Count :: "+keyword+"is null");
		
	}
	
}
