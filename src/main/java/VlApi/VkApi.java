package VlApi;

import com.vk.api.sdk.client.Lang;
import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.UserActor;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import com.vk.api.sdk.objects.users.Fields;
import dataStructure.DataTable;
import dataStructure.Gender;
import dataStructure.StudentFromCSV;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

public class VkApi {
    private VkApiClient vk;
    private Integer userId;
    private String accessToken;

    public VkApi() {
        TransportClient transportClient = HttpTransportClient.getInstance();
        vk = new VkApiClient(transportClient);
        userId = 8002252;
        accessToken = "08e11d2108e11d2108e11d2154089b07ed008e108e11d2169724fc79ce6861c543b2666";
    }

    public String getData() {
        UserActor actor = new UserActor(userId, accessToken);
        try {
            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(vk.users().get(actor).userIds("398088566")
                    .fields(Fields.BDATE, Fields.PHOTO_MAX_ORIG, Fields.CITY, Fields.ABOUT)
                    .lang(Lang.RU).executeAsString());
            JSONArray jsonArray = (JSONArray) jsonObject.get("response");
            for (Object el : jsonArray) {
                JSONObject info = (JSONObject) el;
                System.out.println(String.format("%s %s %s", info.get("first_name"),
                        info.get("last_name"),
                        info.get("bdate")));
            }
            return jsonArray.toString();
        } catch (ClientException | ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserInfo(String groupId, DataTable table) throws ClientException {
        UserActor actor = new UserActor(userId, accessToken);

        JSONParser parser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) parser.parse(vk.groups()
                    .getMembers(actor)
                    .groupId(groupId)
                    .count(1000).offset(0)
                    .fields(Fields.BDATE, Fields.PHOTO_MAX_ORIG, Fields.CITY, Fields.ABOUT, Fields.SEX)
                    .lang(Lang.RU)
                    .executeAsString());
            JSONObject j = (JSONObject) jsonObject.get("response");
            JSONArray jsonArray = (JSONArray) j.get("items");
            for (Object element : jsonArray) {
                JSONObject userInfo = (JSONObject) element;
                var username = String.format("%s %s", userInfo.get("last_name"), userInfo.get("first_name"));
                if (table.containStudent(username)) {
                    JSONObject city = (JSONObject) userInfo.get("city");
                    StudentFromCSV student = table.getStudent(username);
                    if (userInfo.get("bdate") != null)
                        student.setBirthDay(userInfo.get("bdate").toString());
                    student.setFirstname(userInfo.get("first_name").toString());
                    student.setLastname(userInfo.get("last_name").toString());
                    student.setUserVkId(userInfo.get("id").hashCode());
                    student.setLinkPhoto(new URL(userInfo.get("photo_max_orig").toString()));
                    student.setGender(userInfo.get("sex").equals(2l) ? Gender.MALE : Gender.FEMALE);
                    if (city != null)
                        student.setCity(city.get("title").toString());
                }
            }
        } catch (ParseException | MalformedURLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
