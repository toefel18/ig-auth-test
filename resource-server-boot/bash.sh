
curl -X POST "https://christophe-test-pool.auth.eu-west-1.amazoncognito.com/oauth2/token" \
-H 'Content-Type: application/x-www-form-urlencoded' \
-H 'Authorization: Basic MnM1dXZnZ3JvM2thcnFiMDVnMW0wbWwwZTU6YjQyMHZxbzk5dG8wNHM3bTYzaGQzNHRuZTVraDdkZ25oZXFzcm50cGNiOHQzamllNGRx' \
-d "grant_type=client_credentials" \
-d "client_id=2s5uvggro3karqb05g1m0ml0e5" \
-d "scope=http://localhost:8080/customer.read"




curl -X GET "https://christophe-test-pool.auth.eu-west-1.amazoncognito.com/oauth2/userInfo" \
-H 'Authorization: Bearer eyJraWQiOiJNU2UwMTJ3d2FWZGl3RVlNVCtZMUJoWW1teVNCVlhMcVArRGNPamt6VHo4PSIsImFsZyI6IlJTMjU2In0.eyJzdWIiOiIyczV1dmdncm8za2FycWIwNWcxbTBtbDBlNSIsInRva2VuX3VzZSI6ImFjY2VzcyIsInNjb3BlIjoiaHR0cDpcL1wvbG9jYWxob3N0OjgwODBcL2N1c3RvbWVyLnJlYWQiLCJhdXRoX3RpbWUiOjE2MDcyODMzNzksImlzcyI6Imh0dHBzOlwvXC9jb2duaXRvLWlkcC5ldS13ZXN0LTEuYW1hem9uYXdzLmNvbVwvZXUtd2VzdC0xXzkxTEc3SUdQVyIsImV4cCI6MTYwNzI4Njk3OSwiaWF0IjoxNjA3MjgzMzc5LCJ2ZXJzaW9uIjoyLCJqdGkiOiI2ODJlODQ4Ni1mZWUxLTRmZTctOWRlMy0xZGFhM2FjMTA1ZDYiLCJjbGllbnRfaWQiOiIyczV1dmdncm8za2FycWIwNWcxbTBtbDBlNSJ9.BrGqzOOFjwYX7wfDUoS_H4OxE3MUwo9wZ-Hsxrt5KBB4h97q2UKUNpe_PEjWOyxwcigJ2hyGkTa2fAy4kEvdyZTQ3NhW_B37odZwL9mbsYB2WadMtxMOgcol0V_LBzeartWh0uu8i558WG6csst7df9l4sJvplxNxPlUq9_ZUunwTfaHocayxEvH3b3nRk_Wx1q4611yT7f6iSPHl03GqzxLCuStRuklE2HCN1ECW2Eu51iAwlVJ90-KB8jtnLtyUyvlzJF_TZ-vk05lCnF41NuxdgzEuW3cCvhdfcievWjKz3rrk-yvrtczohnUDlRzWj_-VrHSLz_xxLCttm1WEg'




curl -X POST https://cognito-idp.eu-west-1.amazonaws.com/eu-west-1_91LG7IGPW/token \
-H 'Content-Type: application/x-www-form-urlencoded' \
-H 'Authorization: bearer MnM1dXZnZ3JvM2thcnFiMDVnMW0wbWwwZTU6YjQyMHZxbzk5dG8wNHM3bTYzaGQzNHRuZTVraDdkZ25oZXFzcm50cGNiOHQzamllNGRx' \
-d "grant_type=client_credentials&client_id=2s5uvggro3karqb05g1m0ml0e5&scope=http://localhost:8080/customer.read&redirect_uri=http://localhost:8080/customers"
