package com.example.less_79_xmlpullparser;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;

public class MainActivity extends AppCompatActivity {
    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String tmp = "";

        try {
            XmlPullParser xpp = prepareXpp();
            while ((xpp.getEventType() != XmlPullParser.END_DOCUMENT)) {

                switch (xpp.getEventType()) {

                    case XmlPullParser.START_DOCUMENT:
                        Log.d(LOG_TAG, "START_DOCUMENT");
                        break;

                    case XmlPullParser.START_TAG:
                        Log.d(LOG_TAG,
                                "START_TAG: name = " + xpp.getName() +
                                        ", depth = " + xpp.getDepth() +
                                        ", attrCount = " + xpp.getAttributeCount());

                        tmp = "";

                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                            tmp = tmp + xpp.getAttributeName(i) + " = " +
                                    xpp.getAttributeValue(i) + ", ";
                        }

                        if (!TextUtils.isEmpty(tmp))
                            Log.d(LOG_TAG, "Attributes: " + tmp);
                        break;

                    case XmlPullParser.END_TAG:
                        Log.d(LOG_TAG, "END TAG: name " + xpp.getName());
                        break;

                    case XmlPullParser.TEXT:
                        Log.d(LOG_TAG, "text = " + xpp.getText());
                        break;

                    default: break;

                }
                xpp.next();
            }
            Log.d(LOG_TAG, "END_DOCUMENT");
        }

        catch (Exception e) { }
    }

    private XmlPullParser prepareXpp() {
        return getResources().getXml(R.xml.data);
    }

}