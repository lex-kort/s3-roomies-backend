package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.AccessToken;

public interface AccessTokenEncoder {
    String encode(AccessToken accessToken);
}
