# 🧠 Reddit Sentiment Analyzer

This full-stack web application lets users **analyze public sentiment** about any topic using **Reddit data** and **AWS Comprehend**. It classifies discussions as **Positive**, **Negative**, or **Neutral** and displays the results on a clean dashboard with **pie chart visualizations**.

---

## 📌 Features

- 🔍 Enter any topic to analyze Reddit discussions
- 📊 Sentiment analysis (Positive, Negative, Neutral) using AWS Comprehend
- 🧵 Fetches live Reddit threads using Reddit's public API
- 📈 Pie chart dashboard built with React + Chart.js
- 🌐 Full-stack: Spring Boot backend, React frontend, AWS AI services

---

## ⚙️ Tech Stack

| Layer      | Tech                              |
|------------|-----------------------------------|
| Frontend   | React, Tailwind CSS, Axios, Vite  |
| Backend    | Spring Boot, Maven                |
| Cloud      | AWS Comprehend (NLP)              |
| External API | Reddit JSON API (`search.json`) |

---

## 🚀 How to Run the Project

### 🖥️ 1. Clone the Repository

```bash
git clone https://github.com/Pavani167/reddit-sentiment-analyzer.git
cd reddit-sentiment-analyzer
