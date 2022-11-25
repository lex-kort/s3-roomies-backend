package nl.fontys.s3.studenthousing.core.interfaces;

import nl.fontys.s3.studenthousing.domain.AccessToken;

public interface AccessTokenDecoder {
    AccessToken decode(String accessTokenEncoded);
}
