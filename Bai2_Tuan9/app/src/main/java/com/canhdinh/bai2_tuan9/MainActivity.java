package com.canhdinh.bai2_tuan9;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView lv;
    ArrayList<SanPham> data = new ArrayList<>();
    SanPhamAdapter Adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lv = findViewById(R.id.lv);


        Adapter = new SanPhamAdapter(this, R.layout.listview_item_row, data);
        lv.setAdapter(Adapter);

        new SanPhamAsync().execute("http://192.168.43.240:81/Bai2Buoi9/Select_SanPham.php");
    }

    class SanPhamAsync extends AsyncTask<String, Integer, SanPham> {


        @Override
        protected SanPham doInBackground(String... strings) {
            String UrlWebservice = strings[0];// lấy địa chỉ Webservice được truyền vào
            String JsonString = new ReadJson().getJSONStringFromURL(UrlWebservice);
            ArrayList<SanPham> datas = new Gson().fromJson(JsonString, new TypeToken<ArrayList<SanPham>>() {
            }.getType());
            for (int i = 0; i < datas.size(); i++) {
                SanPham sanPham = datas.get(i);
                data.add(sanPham);
            }
            return null;
        }

        @Override
        protected void onPostExecute(SanPham sanPham) {
            super.onPostExecute(sanPham);
            Adapter.notifyDataSetChanged();
        }
    }


    public class ReadJson {
        private final String TAG = ReadJson.class.getSimpleName();

        public ReadJson() {
        }
        public String getJSONStringFromURL(String sUrl) {
            String response = null;
            try {
                URL url = new URL(sUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();// Khởi tạo đối tượng HttpURLConnection
                InputStream _in = url.openStream();
                System.out.println(_in);
                conn.setRequestMethod("GET");// Phương thức lấy dữ liệu
                InputStream in = new BufferedInputStream(conn.getInputStream());// Tạo luồng đọc dữ liệu
                response = convertStreamToString(in);// Chuyển đổi dữ liệu thu được
            } catch (MalformedURLException e) {
                Log.e(TAG, "MalformedURLException: " + e.getMessage());
            } catch (ProtocolException e) {
                Log.e(TAG, "ProtocolException: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "IOException: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "Exception: " + e.getMessage());
                e.printStackTrace();
            }
            return response;
        }
    }

    private String convertStreamToString(InputStream is) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));// Tạo bộ đệm để đọc dòng dữ liệu
        StringBuilder sb = new StringBuilder();// Đối tượng xây dựng chuỗi từ những dữ liệu đã được đọc

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');// đọc và thêm các dữ liệu đã đọc được từ luồng vào chuỗi.
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }
}
