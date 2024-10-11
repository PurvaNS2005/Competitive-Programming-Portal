[README.md](https://github.com/user-attachments/files/17346453/README.md)
# Competitve Programing Portal

A mobile application designed for competitive programmers to check their profile information from three major platforms—Codeforces, LeetCode, and AtCoder. Users can view their rankings, ratings, problems solved, and more.

Additionally, the app features an in-app calendar that helps users plan their contest participation by viewing past, ongoing, and upcoming contests. Users can also add or delete contests from their personalized calendar.




## Features

- User Account: Allows users to create an account for a more personalized experience, enabling them to easily check ratings and contests.
- Profile Aggregation: View user data from Codeforces, LeetCode, and AtCoder. See your rankings, rating, problems solved, and other stats for each platform.
- Contest Calendar: View past, ongoing, and upcoming contests from all three platforms.
- Personalized Calendar: Users can store relevant contests in their calendar and delete them once the contest day has passed.


## Screenshots
![image](https://github.com/user-attachments/assets/9b0b62ad-84f5-40e8-ba51-52bba622e416)
![image](https://github.com/user-attachments/assets/f9d6c24a-aea3-4189-b87e-8fb03ea903fa)
![Screenshot 2024-10-11 175448](https://github.com/user-attachments/assets/cfa1a05e-06ae-4118-9c2c-697398b6dd9c)
![Screenshot 2024-10-11 180913](https://github.com/user-attachments/assets/2e11dcb1-0c14-468f-9421-0f17b407ccaa)
![Screenshot 2024-10-11 180942](https://github.com/user-attachments/assets/d0a42a4a-833e-4448-98e8-db28ccc30255)
![Screenshot 2024-10-11 181014](https://github.com/user-attachments/assets/d91a5e0b-f591-4d26-9787-de1103cba887)
![image](https://github.com/user-attachments/assets/e46c3670-3902-49f8-bf9d-013fc01e21e3)





## Usage

- Signup/Login:
    Register or log in using Firebase Authentication.
- Profile Page:
    View your/your friend's profile data from Codeforces, LeetCode, and AtCoder, including your ratings, problems solved, and ranks.
- Contest Calendar:  
    Browse contests from various platforms.
    Add or delete contests to manage your participation using the in-app calendar.


## Getting Started
#### Prerequisites:
  -  Android Studio (latest version)
  - Firebase Project
  - APIs from Codeforces, LeetCode, and AtCoder
#### Setup:
- Clone the Repository:

      git clone https://github.com/your-username/competitive-programming-portal.git
- Open in Android Studio:

    Open Android Studio and select File > Open.
    Navigate to the project folder and open it.
- Configure Firebase:

    Create a Firebase project at Firebase Console.
    Download the google-services.json file and place it in the app directory.
    Enable Firebase Authentication and Firestore in the Firebase console.
- API Keys and Endpoints:

    Get API access from Codeforces, LeetCode, and AtCoder. Follow their respective API documentation.
    Configure the API URLs in your app code.
- Run the App:

    Build the app in Android Studio and run it on an emulator or a physical device.


## API Reference

#### Get codeforces user info.

```http
  GET https://codeforces.com/api/user.info?handles=${handles}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `handles` | `string` | **Required**. Codeforces handle of the user to fetch. |

#### Get codeforces contests

```http
  GET  https://codeforces.com/api/contest.list?gym=false
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `gym`      | `boolean` | **Required**. false to exclude gym contests |

#### Get Atcoder contests
```http
GET https://kenkoooo.com/atcoder/resources/contests.json
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `None`      |  | This endpoint does not require any parameters. |

#### Get Atcoder user details
```http
GET https://kenkoooo.com/atcoder/atcoder-api/v3/user/ac_rank
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `user`      | `string` | The handle of the user. |



#### Get leetcode past contest details
```http
GET https://alfa-leetcode-api.onrender.com/{handle}/contest/history
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `handle`      | `string` | The leetcode user handle. |

#### Get Leetcode user details
```http
  GET https://alfa-leetcode-api.onrender.com/userProfile/{userName}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `username` | `string` | The leetcode user handle. |

