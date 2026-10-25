# BootOS starter (fixed)

This is a starter Android app and GitHub Actions build workflow.

## Build on GitHub
1. Create a GitHub repository named `BootOS`.
2. Extract this ZIP and upload the *contents* to the repository root (not the ZIP itself).
3. Open **Actions** and enable workflows if asked.
4. Run **Build BootOS APK**.
5. Download the `BootOS-debug-apk` artifact from a successful workflow run.

## Important
This starter does not boot iOS, flash partitions, or unlock the bootloader. Bootloader status is shown as UNKNOWN because ordinary Android apps cannot reliably query it on every device. Real iOS booting on the Moto G60 has not been established.
