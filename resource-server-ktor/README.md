https://docs.aws.amazon.com/cognito/latest/developerguide/token-endpoint.html


curl -X POST https://cognito-idp.eu-west-1.amazonaws.com/eu-west-1_91LG7IGPW/oauth2/token \
-H 'Content-Type: application/x-www-form-urlencoded' \
-H 'Authorization: NGZudjBmYjhlaTA3MjFzODN1NnNpcmk4YXU6NTZlN3Q3aGkwZGtrdmM5M29zZTA2cW5ydDBwYWpibDFoZmlrbDY0dXNtMTM1cGk4dDZp' \
-d "grant_type=client_credentials" \
-d "client_id=4fnv0fb8ei0721s83u6siri8au" \
-d "scope=http://localhost:8080/customer.read&redirect_uri=http://localhost:8080/secured/customer" \
-d "redirect_uri=http://localhost:8080/secured/customer"
