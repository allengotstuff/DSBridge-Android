package wendu.dsbridge;

import android.util.Log;

import org.json.JSONObject;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by allensun on 1/23/18.
 * on Tubitv.com, allengotstuff@gmail.com
 */
public class JsonUtilTest {
    @Test
    public void buildResultObject() throws Exception {
        final String PLATFORM = "platform";
        final String VERSION = "version";
        final String USER_ID = "user_id";

        String platform = "2.12.9";
        String version = "1123";
        double userId = 32432498234d;

        Map<String, Object> inputMap = new HashMap<>();
        inputMap.put(PLATFORM,platform);
        inputMap.put(VERSION, version);
        inputMap.put(USER_ID, userId);


        JSONObject jsonObject = JsonUtil.buildResultObject(new JSONObject(inputMap));

        JSONObject resultOb = (JSONObject)jsonObject.get("result");

        assertTrue(resultOb.get(PLATFORM).toString() == platform);
        assertTrue(resultOb.get(VERSION).toString() == version);
        assertTrue((double)resultOb.get(USER_ID) == userId);


        Log.d("buildResultObject", jsonObject.toString());
    }

    @Test
    public void buildSuccessMessage() throws Exception {

        final String message = "asdfsadfasd      fdsadf       sdf-2312234-~!!M      fsadf";

        JSONObject jsonObject = JsonUtil.buildSuccessMessage(message);

        assertTrue(jsonObject.get("result").toString().equalsIgnoreCase(message));
    }

    @Test
    public void buildErrorMessage() throws Exception {

        final String message = "asdfsadfasd      fdsadf       sdf-2312234-~!!M      fsadf";

        JSONObject jsonObject = JsonUtil.buildErrorMessage(message);

        assertTrue(jsonObject.get("error").toString().equalsIgnoreCase(message));
    }

    @Test
    public void isValidJSON() throws Exception {
        boolean result = JsonUtil.isValidJSON(null);
        assertFalse(result);

        //
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result","msg");
        result = JsonUtil.isValidJSON(jsonObject.toString());
        assertTrue(result);

        //
        result =  JsonUtil.isValidJSON("");
        assertFalse(result);


        result =  JsonUtil.isValidJSON("{\n" +
                "\t\"age\":100,\n" +
                "\t\"name\":\"mkyong.com\",\n" +
                "\t\"messages\":[\"msg 1\",\"msg 2\",\"msg 3\"]\n" +
                "}");
        assertTrue(result);

        result =  JsonUtil.isValidJSON("sdfsadfsadfsdf");
        assertFalse(result);

        result =  JsonUtil.isValidJSON("{}");
        assertTrue(result);
    }

}