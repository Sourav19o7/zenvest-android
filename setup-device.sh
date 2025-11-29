#!/bin/bash

# Script to set up ADB reverse port forwarding for localhost backend access
# This allows your physical Android device to access localhost:3000 on your computer

echo "Setting up ADB reverse port forwarding..."

# Check if adb is available
if ! command -v adb &> /dev/null; then
    echo "Error: adb not found. Make sure Android SDK is installed."
    exit 1
fi

# List connected devices
echo "Connected devices:"
adb devices

# Set up reverse port forwarding for port 3000
echo ""
echo "Setting up port forwarding (device port 3000 -> host port 3000)..."
adb reverse tcp:3000 tcp:3000

# Verify the setup
echo ""
echo "Active port forwarding rules:"
adb reverse --list


echo ""
echo "✓ Setup complete! Your device can now access your localhost backend at http://localhost:3000"
echo ""
echo "Note: Port forwarding will be removed when you disconnect the device."
echo "Run this script again after reconnecting your device."
