package API.Data;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.qameta.allure.Step;
import io.qameta.allure.okhttp3.AllureOkHttp3;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SendRequest {
    private final OkHttpClient client;
    public SendRequest() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new AllureOkHttp3()
                .setRequestTemplate("custom-http-request.ftl")
                .setResponseTemplate("custom-http-response.ftl"));
        client = httpClient.build();;
    }

    @Step(value = "Method > getJson()")
    public List<Object> getJson(String scheme,String url,String segments,Headers headers,
                                Boolean showResponse) throws Exception {

        HttpUrl httpUrl = new HttpUrl.Builder().scheme(scheme).host(url).addPathSegments(segments).build();

        Response response = client.newCall(getRequestData(httpUrl,headers,"get",null)).execute();
        List<Object> responseData = getResponseData(response);
        if (showResponse) showResponseData(responseData,url);

        response.close();
        return responseData;
    }

    @Step(value = "Method > getJson()")
    public List<Object> getJson(String scheme,String url,String segments,Headers headers,boolean showResponse,
                                String key1,String value1) throws Exception {

        HttpUrl httpUrl = new HttpUrl.Builder().scheme(scheme).host(url).addPathSegments(segments)
                .addQueryParameter(key1,value1)
                .build();

        Response response = client.newCall(getRequestData(httpUrl,headers,"get",null)).execute();
        List<Object> responseData = getResponseData(response);
        if (showResponse) showResponseData(responseData,url);

        response.close();
        return responseData;
    }

    @Step(value = "Method > getJson()")
    public List<Object> getJson(String scheme,String url,String segments,Headers headers,boolean showResponse,
                                String key1,String value1,
                                String key2,String value2) throws Exception {

        HttpUrl httpUrl = new HttpUrl.Builder().scheme(scheme).host(url).addPathSegments(segments)
                .addQueryParameter(key1,value1)
                .addQueryParameter(key2,value2)
                .build();

        Response response = client.newCall(getRequestData(httpUrl,headers,"get",null)).execute();
        List<Object> responseData = getResponseData(response);
        if (showResponse) showResponseData(responseData,url);

        response.close();
        return responseData;
    }

    @Step(value = "Method > postJson()")
    public List<Object> postJson(String scheme,String url,String segments,Headers headers, Boolean showResponse,
                                 String json) throws Exception {

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json,JSON);

        HttpUrl httpUrl = new HttpUrl.Builder().scheme(scheme).host(url).addPathSegments(segments).build();

        Response response = client.newCall(getRequestData(httpUrl,headers,"post",body)).execute();
        List<Object> responseData = getResponseData(response);
        if (showResponse) showResponseData(responseData,url);

        response.close();
        return responseData;
    }

    @Step(value = "Method > postJson()")
    public List<Object> postJson(String scheme,String url,String segments,Headers headers, Boolean showResponse,
                                 String key1,String value1) throws Exception {

        RequestBody body = new FormBody.Builder()
                .add(key1, value1)
                .build();

        HttpUrl httpUrl = new HttpUrl.Builder().scheme(scheme).host(url).addPathSegments(segments).build();

        Response response = client.newCall(getRequestData(httpUrl,headers,"post",body)).execute();
        List<Object> responseData = getResponseData(response);
        if (showResponse) showResponseData(responseData,url);

        response.close();
        return responseData;
    }

    @Step(value = "Method > putJson()")
    public List<Object> putJson(String scheme,String url,String segments,Headers headers, Boolean showResponse,
                                String json) throws Exception {

        final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json,JSON);

        HttpUrl httpUrl = new HttpUrl.Builder().scheme(scheme).host(url).addPathSegments(segments).build();

        Response response = client.newCall(getRequestData(httpUrl,headers,"put",body)).execute();
        List<Object> responseData = getResponseData(response);
        if (showResponse) showResponseData(responseData,url);

        response.close();
        return responseData;
    }

    @Step(value = "Method > deleteJson()")
    public List<Object> deleteJson(String scheme,String url,String segments,Headers headers,
                                Boolean showResponse) throws Exception {

        HttpUrl httpUrl = new HttpUrl.Builder().scheme(scheme).host(url).addPathSegments(segments).build();

        Response response = client.newCall(getRequestData(httpUrl,headers,"delete",null)).execute();
        List<Object> responseData = getResponseData(response);
        if (showResponse) showResponseData(responseData,url);

        response.close();
        return responseData;
    }


    public List<Object> getResponseData(Response response) throws IOException {
        List<Object> responseList = new ArrayList<>();
        responseList.add(response.isSuccessful());
        responseList.add(response.isRedirect());
        responseList.add(response.request().method());
        responseList.add(response.code());
        responseList.add(response.receivedResponseAtMillis()-response.sentRequestAtMillis());
        responseList.add(response.headers());
        responseList.add(response.body().string());
        return responseList;
    }

    public void showResponseData(List<Object> responseList,String url) {
        System.out.println("\n⚡ API URL: "+url);
        System.out.println("♻ Redirect: "+(boolean)responseList.get(1));
        System.out.println("\uD83C\uDFAF Method: "+responseList.get(2));
        System.out.println("\uD83E\uDD16 Code: "+(int)responseList.get(3));
        System.out.println("⏳ Request processing: "+responseList.get(4)+"ms");
        System.out.println("\n\uD83D\uDCCC Headers: "+responseList.get(5));
        System.out.println("\uD83D\uDCDC Body: "+responseList.get(6));
    }

    public Request getRequestData(HttpUrl httpUrl,Headers headers,String methodRequest,RequestBody requestBody) {
        switch (methodRequest) {
            case ("get"):
                return new Request.Builder().url(httpUrl).headers(headers).build();
            case ("post"):
                return new Request.Builder().url(httpUrl).headers(headers).post(requestBody).build();
            case ("put"):
                return new Request.Builder().url(httpUrl).headers(headers).put(requestBody).build();
            case ("delete"):
                return new Request.Builder().url(httpUrl).headers(headers).delete(requestBody).build();
        }
        return null;
    }

}
