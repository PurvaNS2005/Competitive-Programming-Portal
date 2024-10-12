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
![Screenshot 2024-10-12 013019](https://github.com/user-attachments/assets/2d026dd5-d6b7-4476-b970-3c1bef3c03c9)
![Screenshot 2024-10-12 013056](https://github.com/user-attachments/assets/32abe8f8-0ebd-4873-bb77-3bdb131ed6b2)
![Screenshot 2024-10-12 013316](https://github.com/user-attachments/assets/3a28428d-08ff-45e9-b275-4ca642eb4009)
![Screenshot 2024-10-12 013132](https://github.com/user-attachments/assets/4547a80c-b0d3-415c-96d0-524176e3d7fe)
![Screenshot 2024-10-12 013157](https://github.com/user-attachments/assets/b47f7bee-4ba4-4ccb-b94d-524ffbaa2570)
![Screenshot 2024-10-12 013221](https://github.com/user-attachments/assets/18d12ee3-d316-4e72-abdc-19a14199b4b6)
![Screenshot 2024-10-12 013334](https://github.com/user-attachments/assets/f999973f-1e90-492f-a208-3089d4a2a5d8)

![Screenshot 2024-10-12 172454](https://github.com/user-attachments/assets/4f07f9e4-2aeb-4580-85d8-7dce318dd9a4)





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

#### Get codeforces contests.

```http
  GET  https://codeforces.com/api/contest.list?gym=false
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `gym`      | `boolean` | **Required**. false to exclude gym contests |

#### Get Atcoder contests.
```http
GET https://kenkoooo.com/atcoder/resources/contests.json
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `None`      |  | This endpoint does not require any parameters. |

#### Get Atcoder user details.
```http
GET https://kenkoooo.com/atcoder/atcoder-api/v3/user/ac_rank
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `user`      | `string` | The handle of the user. |



#### Get leetcode past contest details.
```http
GET https://alfa-leetcode-api.onrender.com/{handle}/contest/history
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `handle`      | `string` | The leetcode user handle. |

#### Get Leetcode user details.
```http
  GET https://alfa-leetcode-api.onrender.com/userProfile/{userName}
```
| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `username` | `string` | The leetcode user handle. |


