public enum HTTPResponseCode {
    OK(200, "OK"),
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Not Found"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version Not Supported"),
    ;

    private final int code;
    private final String phrase;

    HTTPResponseCode(int code, String phrase) {
        this.code = code;
        this.phrase = phrase;
    }

    @Override
    public String toString() {
        return code + " " + phrase;
    }
}
