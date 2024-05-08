import { useParams } from 'react-router-dom';
import React, { useState } from 'react';
import axios from 'axios';
import { toast, ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';



const ApplyForJob = () => {
    const { id } = useParams(); // Retrieve the jobId parameter from the URL

    const [senderName, setSenderName] = useState('');
    const [describeYourself, setDescribeYourself] = useState('');
    
    const handleSubmit = async (e) => {
      e.preventDefault();

      console.log(id);
      console.log(senderName);
      console.log(describeYourself);

      try {
        // Retrieve authentication token from localStorage, assuming it's stored there
        const token = localStorage.getItem('token');

        // Make sure token is available
        if (!token) {
          throw new Error('Authentication token not found');
        }
  
        const response = await axios.post('http://localhost:5109/api/proposal', {
          jobId: id,  
          senderName,
          describeYourself,
        }, {
          headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${token}` // Include the authorization token
          }
        });
  
        console.log('Proposal submitted successfully:', response.data);
        toast.success('Proposal submitted successfully:');
        // Optionally, you can redirect the user to a different page or show a success message.
      } catch (error) {
        console.error('Error submitting proposal:', error.message);
        // Handle the error appropriately, e.g., display an error message to the user
      }
    };

    return (
        <>
            <link rel='stylesheet' href='assets/ApplyForJob/style.css'></link>
            <div className="uformbold-main-wrapper">
                <div className="uformbold-form-wrapper">
                    <div className="ualert alert-danger my-2"></div>
                    <form onSubmit={handleSubmit}>
                        <div className="uformbold-form-title">
                            <h2 className="">Job Application</h2>
                        </div>
                        <div className="uformbold-input-flex">
                            <div>
                                <label htmlFor="firstname" className="uformbold-form-label">
                                    First name
                                </label>
                                <input
                                    type="text"
                                    name="firstname"
                                    id="firstname"
                                    className="uformbold-form-input"
                                    value={senderName}
                                    onChange={(e) => setSenderName(e.target.value)}
                                />
                            </div>
                        </div>
                        <div className="uformbold-input-flex">
                            <div>
                                <label htmlFor="describeYourself" className="uformbold-form-label">
                                    Talk about your skills
                                </label>
                                <textarea
                                    name="describeYourself"
                                    id="describeYourself"
                                    className="uformbold-form-input"
                                    value={describeYourself}
                                    onChange={(e) => setDescribeYourself(e.target.value)}
                                ></textarea>
                            </div>
                        </div>
                        <button type="submit" className="uformbold-btn m-2">
                            Submit Proposal
                        </button>
                    </form>
                </div>
            </div>
            <ToastContainer />
        </>
    );
};

export default ApplyForJob;
