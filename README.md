# Zenvest Android App

AI-powered financial companion app for India's gig workers and professionals.

## Project Structure

```
zenvest-app/
├── app/                          # Main application module
├── core/                         # Shared core module
│   ├── common/                   # Extensions, session, utilities
│   ├── network/                  # Retrofit, API client, interceptors
│   └── ui/                       # Theme, components
├── feature-auth/                 # Authentication (login, OTP)
├── feature-onboarding/           # Multi-step onboarding flow
├── feature-consent/              # Bank account linking
└── feature-dashboard/            # Main dashboard screens
    ├── home/                     # Dashboard home with AI personas
    ├── goals/                    # Goal tracking
    ├── investments/              # SIP management
    ├── debt/                     # Debt management
    ├── emergency/                # Emergency fund tracker
    ├── profile/                  # User profile
    ├── settings/                 # App settings
    └── help/                     # Help & support
```

## Tech Stack

- **Language**: Kotlin 2.1.10
- **UI**: Jetpack Compose with Material 3
- **Architecture**: MVVM + Clean Architecture
- **DI**: Hilt
- **Networking**: Retrofit + OkHttp
- **State Management**: Flow/StateFlow
- **Navigation**: Jetpack Compose Navigation
- **Min SDK**: 26 | **Target SDK**: 36

## Setup

1. Clone the repository
2. Open in Android Studio Ladybug or later
3. Sync Gradle
4. Update `API_BASE_URL` in `app/build.gradle.kts`
5. Add your Google Client ID for OAuth
6. Run the app

## Features

- Email OTP & Google Sign-In authentication
- Personalized multi-step onboarding
- Secure bank account linking (AA Framework)
- AI-powered financial personas:
  - Budget Coach
  - Investment Advisor
  - Debt Expert
- Goal tracking with progress visualization
- SIP/Investment management
- Debt payoff strategies (Snowball/Avalanche)
- Emergency fund calculator
- Profile & Settings management

## API Integration

The app integrates with the Zenvest backend:
- `/api/auth/*` - Authentication endpoints
- `/api/onboarding` - Onboarding submission
- `/api/consent/*` - Bank consent management
- `/api/goals/*` - Goal CRUD operations
- `/api/investments/*` - Investment management
- `/api/debts/*` - Debt tracking
- `/api/emergency-fund/*` - Emergency fund
- `/api/transactions/*` - Transaction history

## Build

```bash
./gradlew assembleDebug
./gradlew assembleRelease
```

## License

Proprietary - All rights reserved
