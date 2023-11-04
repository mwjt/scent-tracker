package pl.pwr.scenttracker.security;

public interface SecurityConstants {
    String SECRET = "WeVwc1HmB2cuMkeJD3JMzQH438yF02L8y2TZuaTK/WNUkPwIFDWCH9W3qOtSvSrhXS0jWl0jOILBoErKWx1L5Zj/BHwa8gmnRnQoPkZd9tuaWJIPPnCZ3yUgFhnjsC/7Wxznr748aS9Pn2IVysZh5BZvdMndB8ZjohqGGvCqrmSqaZNd2FV9NL9nYHiEeALpF+ZNpBJmj2eT57NoD4NRG1JBxzVFs7tvGpW+KA==";
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
    String SIGN_UP_URL = "/users/sign-up";
    long EXPIRATION_TIME = 864_000_000;
}
