package controllers;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.HttpAuthenticator;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class WebControllerTest {

    @Test
    public void UserDoesNotHavePermission_ToGetAllApplicationForms_then401IsReceived()
            throws IOException {

        // Given
        HttpUriRequest request = new HttpGet("https://it-academy-app-back.herokuapp.com/applications/");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_UNAUTHORIZED));
    }

    @Test
    public void UserDoesNotHavePermission_ToGetApplicationForm_then401IsReceived()
            throws IOException {

        // Given
        String hashId = "3aba8b75320646129a96dbcef6f7829cdcea47c4e0f477121f13d0a30c9c2d35";
        HttpUriRequest request = new HttpGet("https://it-academy-app-back.herokuapp.com/application/" + hashId);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_UNAUTHORIZED));
    }

    @Test
    public void UserHavePermission_ToGetApplicationForm_then200IsReceived()
            throws IOException {

        // Given
        String hashId = "3aba8b75320646129a96dbcef6f7829cdcea47c4e0f477121f13d0a30c9c2d35";
        HttpAuthenticator httpAuthenticator = new HttpAuthenticator();
        HttpUriRequest request = new HttpGet("https://it-academy-app-back.herokuapp.com/application/" + hashId);

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

        // Then
        assertThat(
                httpResponse.getStatusLine().getStatusCode(),
                equalTo(HttpStatus.SC_UNAUTHORIZED));
    }
}