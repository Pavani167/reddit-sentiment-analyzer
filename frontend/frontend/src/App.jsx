import { useState } from 'react';
import SentimentForm from './components/SentimentForm';
import SentimentChart from './components/SentimentChart';

const App = () => {
  const [sentimentResult, setSentimentResult] = useState(null);

  return (
    <div className="min-h-screen px-4 py-10 bg-gray-100 dark:bg-gray-900 text-gray-900 dark:text-white transition-colors">
      <div className="max-w-3xl mx-auto text-center">
        <h1 className="text-3xl font-bold mb-4">🔍 Reddit Sentiment Analyzer</h1>
        <p className="text-gray-600 dark:text-gray-400 mb-6">
          Enter a topic to see how people feel about it on Reddit using AWS Comprehend.
        </p>
        <SentimentForm onResult={setSentimentResult} />
        {sentimentResult && (
          <div className="mt-10">
            <h2 className="text-xl font-semibold mb-4">Sentiment Overview</h2>
            <SentimentChart data={sentimentResult} />
          </div>
        )}
      </div>
    </div>
  );
};

export default App;
