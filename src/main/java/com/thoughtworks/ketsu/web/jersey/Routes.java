package com.thoughtworks.ketsu.web.jersey;

import javax.ws.rs.core.UriInfo;
import java.net.URI;

public class Routes {

    private final String baseUri;

    public Routes(UriInfo uriInfo) {
        baseUri = uriInfo.getBaseUri().toASCIIString();
    }

    public URI userUrl(String uid) {
        return URI.create(String.format("%susers/%s", baseUri, uid));
    }

    public URI productUrl(String id) {
        return URI.create(String.format("%sproducts/%s", baseUri, id));
    }
}
