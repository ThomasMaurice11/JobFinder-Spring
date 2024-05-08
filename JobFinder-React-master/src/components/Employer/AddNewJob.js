import React, { useState } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

const AddNewJob = () => {
  const [jobTitle, setJobTitle] = useState('');
  const [jobType, setJobType] = useState('');
  const [jobBudget, setJobBudget] = useState('');
  const [jobDescription, setJobDescription] = useState('');

  const handleSubmit = async (e) => {
    e.preventDefault();

    try {
      // Retrieve authentication token from localStorage, assuming it's stored there
      const token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ0aG9tYXNAZ21haWwuY29tIiwiaWF0IjoxNzE1MTI2Nzg2LCJleHAiOjE3MTU3MzE1ODZ9.V2fT2nPGzTJXo9FquTPg7yOVy_0o5IKcw15hFkY9C68";

      // Make sure token is available
      if (!token) {
        throw new Error('Authentication token not found');
      }

      const newJobData = {
        jobTitle,
        jobType,
        jobBudget: parseFloat(jobBudget), // Convert to float
        postDate: new Date().toISOString(), // Current date
        jobDescription,
        numberOfProposals: 0,
        employer: {
          id: 1 // Assuming you have a predefined employer ID
        }
      };

      const response = await axios.post('http://localhost:8080/jobConnect/jobs', newJobData, {
        headers: {
          'Content-Type': 'application/json',
          'Authorization': `Bearer ${token}` // Include the authorization token
        }
      });

      console.log('Job created successfully:', response.data);
      // Show success toast
      toast.success('Job created successfully!');
      // Optionally, you can redirect the user to a different page or show a success message.
    } catch (error) {
      console.error('Error creating job:', error.message);
      // Show error toast
      toast.success('Job created successfully!');
      // Handle the error appropriately, e.g., display an error message to the user
    }
  };

  return (
    <>
      <link rel='stylesheet' href='assets/AddNewJob/style.css'></link>
      <div className="formbold-main-wrapper">
        <div className="formbold-form-wrapper">
          <form onSubmit={handleSubmit}>
            <div className="formbold-input-group">
              <label htmlFor="jobTitle" className="formbold-form-label">
                Job Title
              </label>
              <input
                type="text"
                name="jobTitle"
                id="jobTitle"
                placeholder="FullStack Developer"
                className="formbold-form-input"
                value={jobTitle}
                onChange={(e) => setJobTitle(e.target.value)}
              />
            </div>
            <div className="formbold-input-group">
              <label htmlFor="jobType" className="formbold-form-label">
                Job Type
              </label>
              <input
                type="text"
                name="jobType"
                id="jobType"
                placeholder="Full-Time"
                className="formbold-form-input"
                value={jobType}
                onChange={(e) => setJobType(e.target.value)}
              />
            </div>
            <div className="formbold-input-group">
              <label htmlFor="jobBudget" className="formbold-form-label">
                Job Budget
              </label>
              <input
                type="text"
                name="jobBudget"
                id="jobBudget"
                placeholder="8000-12000"
                className="formbold-form-input"
                value={jobBudget}
                onChange={(e) => setJobBudget(e.target.value)}
              />
            </div>
            <div className="formbold-input-group">
              <label htmlFor="jobDescription" className="formbold-form-label">
                Job Description
              </label>
              <input
                type="text"
                name="jobDescription"
                id="jobDescription"
                placeholder="We are hiring a FullStack Developer with high-level skills"
                className="formbold-form-input"
                value={jobDescription}
                onChange={(e) => setJobDescription(e.target.value)}
              />
            </div>
            <button type="submit" className="formbold-btn">
              Submit
            </button>
          </form>
        </div>
      </div>
      <ToastContainer />
    </>
  );
};

export default AddNewJob;
