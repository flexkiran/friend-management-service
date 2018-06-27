# friend-management-service
API server that does simple "Friend Management" 

# User Stories
# 0. User can register email id using 
URL - /api/email/register
Methos - POST
Request Format
```
{
  "email": "john@example.com"
}
```
Response Format
```
{
  "success":true
}
```
Error codes
2001	- Email address is already registered

# 1. User can add friend connection between two email ids
After successfully adding friend . 
Both email ids will automatically subscribe for updates from each other
URL - /api/friend/add
Methos - POST
Request Format
```
{
  "friends": [
    "john@example.com",
    "kane@example.com"
  ]
}
```
Response Format
```
{
  "success":true
}
``` 
  
  
Error codes 
2002	- Email address is not registered  
3001  - Email addresses are already connected as friends  
3003  - One email have blocked another email's updates

# 2. User can retrieve the friends list for an email address. 
URL - /api/friend/getall
Methos - POST
Request Format
```
{
  email: 'andy@example.com'
}
```
Response Format
```
{
  "friends" :
    [
      'john@example.com'
    ],
  "count" : 1 ,
  "success": true
}
```
Error codes
2002	- Email address is not registered

# 3. User can retrieve the common friends list between two email addresse
URL - /api/friend/getcommon
Methos - POST
Request Format
```
{
  friends:
    [
      'andy@example.com',
      'john@example.com'
    ]
}
```
Response Format
```
{
  "success": true,
  "friends" :
    [
      'common@example.com'
    ],
  "count" : 1   
}
```
Error codes
2002	- Email address is not registered




