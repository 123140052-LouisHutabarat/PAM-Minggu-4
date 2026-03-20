# My Profile App

Aplikasi **My Profile App** adalah sebuah aplikasi kartu nama digital interaktif yang dibangun menggunakan **Compose Multiplatform (Kotlin)**. Aplikasi ini dikembangkan sebagai bagian dari tugas praktikum Pengembangan Aplikasi Mobile (Minggu 4), dengan fokus utama pada penerapan arsitektur modern Android.

## Fitur Utama

Aplikasi ini tidak hanya menampilkan antarmuka yang statis, tetapi juga dilengkapi dengan berbagai fitur dinamis yang diatur menggunakan arsitektur pemisahan *concern* yang baik:

1. **Arsitektur MVVM (Model-View-ViewModel)**
   - Menggunakan `ProfileViewModel` untuk memisahkan logika aplikasi dari tampilan (UI).
   - Memanfaatkan `StateFlow` untuk mengamati dan memperbarui *state* UI secara *real-time*.
   - Menerapkan *Data Class* `ProfileUiState` sebagai wadah penyimpan status (*Single Source of Truth*).

2. **Fitur Edit Profil (State Hoisting)**
   - Pengguna dapat memperbarui informasi profil secara langsung dari dalam aplikasi.
   - Kolom isian yang dapat diubah meliputi: Nama Lengkap, Deskripsi (Bio), Email, Nomor Telepon, dan Lokasi.
   - Menerapkan konsep *State Hoisting* pada komponen `TextField` untuk mengelola input sebelum disimpan atau dibatalkan.

3. **Dark Mode Toggle (Tema Dinamis)**
   - Dilengkapi dengan *Switch* interaktif untuk beralih antara Mode Terang (*Light Mode*) dan Mode Gelap (*Dark Mode*).
   - Seluruh komponen UI (latar belakang, teks, ikon, dan kartu) akan beradaptasi secara otomatis mengikuti tema yang dipilih.

## Cara Penggunaan

1. **Melihat Profil:** Saat aplikasi pertama kali dibuka, layar akan menampilkan kartu profil lengkap dengan informasi kontak.
2. **Mengganti Tema:** Klik *toggle/switch* di bagian atas layar untuk mengubah tampilan aplikasi menjadi Mode Gelap atau Mode Terang.
3. **Mengedit Data:** - Tekan tombol **"Edit Profile"** di bagian bawah kartu.
   - Layar akan berubah menjadi *Form* pengisian data.
   - Ketikkan informasi baru yang diinginkan, kemudian tekan **"Simpan"** untuk menerapkan perubahan, atau **"Batal"** untuk kembali ke tampilan awal tanpa menyimpan.

## Preview Aplikasi

Berikut adalah tangkapan layar (screenshot) dari aplikasi My Profile App:

| Tampilan Utama (Mode Terang) | Tampilan Utama (Mode Gelap) |
| :---: | :---: |
| ![Light Mode View](https://github.com/user-attachments/assets/597d5455-2292-40c2-bbe2-9c1cd019eed1) | ![Dark Mode View](https://github.com/user-attachments/assets/c6372186-5d29-4a63-92a0-5711f5142c59) |

| Form Edit (Mode Terang) | Form Edit (Mode Gelap) |
| :---: | :---: |
| ![Light Mode Edit](https://github.com/user-attachments/assets/033161ec-5feb-41e4-8c9f-199839f4214d) | ![Dark Mode Edit](https://github.com/user-attachments/assets/1cade380-738c-4c27-859d-f5ae6e834c07) |

---
