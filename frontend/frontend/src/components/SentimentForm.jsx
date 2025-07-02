import { useState } from 'react';
import axios from 'axios';

const SentimentForm = ({ onResult }) => {
  const [topic, setTopic] = useState('');
  const [loading, setLoading] = useState(false);

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!topic) return;
    setLoading(true);
    try {
      const res = await axios.get('http://localhost:8080/api/sentiment', {
        params: { topic }
      });
      onResult(res.data);
    } catch (err) {
      console.error('Error:', err);
      alert('Failed to fetch sentiment.');
    } finally {
      setLoading(false);
    }
  };

  return (
    <form onSubmit={handleSubmit} className="flex gap-2 w-full max-w-2xl mx-auto mt-4">
      <input
        className="flex-1 p-3 rounded-lg bg-white dark:bg-gray-800 border border-gray-300 dark:border-gray-700"
        type="text"
        placeholder="Enter a topic like 'AI', 'Budget 2024', 'Netflix'..."
        value={topic}
        onChange={(e) => setTopic(e.target.value)}
      />
      <button
        className="px-4 py-2 bg-blue-600 hover:bg-blue-700 text-white rounded-lg"
        type="submit"
        disabled={loading}
      >
        {loading ? 'Analyzing...' : 'Analyze'}
      </button>
    </form>
  );
};

export default SentimentForm;
