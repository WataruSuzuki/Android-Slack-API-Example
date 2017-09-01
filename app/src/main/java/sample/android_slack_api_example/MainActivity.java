package sample.android_slack_api_example;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Map;

import allbegray.slack.SlackClientFactory;
import allbegray.slack.webapi.SlackWebApiClient;

public class MainActivity extends AppCompatActivity {

    private String slackToken = "";
    private SlackWebApiClient mWebApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] items = {"Auth", "Get emoji list", "Set status"};
        ListView lv = (ListView) findViewById(R.id.listView);
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, items);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == MySampleMenu.EMOJI.ordinal()) {
                    getEmojiList();
                }
            }
        });

        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitAll().build());
    }

    private void getEmojiList() {
        mWebApiClient = SlackClientFactory.createWebApiClient(slackToken);
        Map<String, String> emojiList =  mWebApiClient.getEmojiList();
        System.out.println(emojiList.toString());
    }

    private enum MySampleMenu {
        AUTH("Auth"),
        EMOJI("Get emoji list"),
        STATUS("Set status");

        private final String name;

        private MySampleMenu(final String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
