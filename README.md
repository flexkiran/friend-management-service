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

# 4. User should be able to subscribe for updates from one email address to another email address  
URL - /api/notification/subscribe .  
Methos - POST  
Request Format
```
{ 
  'requestor':'andy@example.com',
  'target':'john@example.com' 
}
```
Response Format
```
{
  "success": true
}
```
Error codes   

2002	- Email address is not registered     
4001	- Already subscribed for updates    

# 5. User should be able to block updates for one email address from another email address 
     (No matter wheteher emails are friend or not)
URL - /api/notification/unsubscribe  
Methos - POST
Request Format
```
{ 
  'requestor':'andy@example.com',
  'target':'john@example.com' 
}
```
Response Format
```
{
  "success": true
}
```
Error codes   

2002	- Email address is not registered     
5001	- Already unsubscribed from further updates        

# 6. User should be able to send message     
URL - /api/message/send  
Methos - POST
Request Format
```
{ 
  'sender':'andy@example.com',
  'text':'Hello john@example.com how are you?' 
}
```
Response Format
```
{
  "recipients": [
    "john@example.com"
  ],
  "success": true
}
```
Error codes   

2002	- Email address is not registered     


###   Error format   

```  
{
  "status": "BAD_REQUEST",
  "timestamp": "27-06-2018 11:19:30",
  "message": "validation failed",
  "errorCode": 0,
  "errors": [
    "Friend list should contain exactly 2 emails",
    "Invalid email ids"
  ]
}
```

###   Error Codes and messages

```
2001 - "Email address is already registered"
2002 - "Email address is not registered"
3001 - "Email addresses are already connected as friends"
3002 - "Email addresses are not connected as friends"
3003 - "One email have blocked another email's updates"
4001 - "Already subscribed for updates"
5001 - "Already unsubscribed from further updates"
```
    
###  Documenatation

This project uses swagger for documentation.
Once you run this app documenatation will be available on
http://host:port/swagger-ui.html

Cucumber stories can be found [here](https://github.com/flexkiran/friend-management-service/blob/master/src/itest/resources/friendmanagement.feature)




 






