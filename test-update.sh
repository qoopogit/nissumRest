curl -v --header "Content-Type: application/json" \
  --request PUT \
  --data '{ "id": "89c1e0af-f7a7-4135-a32b-8142e995afb1", "name":"Alberto Garcia","password":"beto12345","email":"alberto.garcia@gmail.com", "phones":[{"id": "30250335-c408-4062-8afd-4907d5d21222","number":"1234567","cityCode":"02","countryCode":"+593"},{"id": "426e10c0-3805-4183-ac25-4e567c47497e","number":"7654321","cityCode":"06","countryCode":"+593"} ] }' \
  http://localhost:8080/users/update


