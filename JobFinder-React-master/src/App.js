import React from 'react';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import AddNewJob from './components/Employer/AddNewJob';
import './App.css'; // Import your CSS file if needed
import Jobs from './components/JobSeeker/Jobs';
import ApplyForJob from './components/JobSeeker/ApplyForJob';
import AddNewUser from './components/Admin/AddNewUser';
import JobsToAccept from './components/Admin/JobsToAccept';
import Proposal from './components/Employer/Proposal';
import UsersList from './components/Admin/UsersList';
import SavedJobs from './components/JobSeeker/SavedJobs';
import UpdateUser from './components/Admin/UpdateUser';

import JNavbar from './components/JobSeeker/JNavbar';
import ENavbar from './components/Employer/ENavbar';
import Login from './components/Reg/Login';
import SignUp from './components/Reg/SignUp';
import AHome from './components/Admin/AHome';
import { AuthProvider } from './Context/AuthContext'; // Import AuthProvider
import ProposalsToAccept from './components/Employer/ProposalsToAccept';
import SendMessage from './components/JobSeeker/SendMessage';
import MyProposals from './components/JobSeeker/MyProposals';
import MyMessages from './components/JobSeeker/MyMessages';
import ANavbar from './components/Navbar/ANavbar';


function App() {
  return (
    <Router>
      <AuthProvider> {/* Wrap your entire application with AuthProvider */}
    
      <ANavbar />

        <Routes>
      
          <Route path="/addNewJob" element={<AddNewJob />} />
          <Route path="/Jobs" element={<Jobs />} />
          <Route path="/ApplyForJob/:id" element={<ApplyForJob />} />
          <Route path="/AddNewUser" element={<AddNewUser />} />
          <Route path="/JobsToAccept" element={<JobsToAccept />} />
          <Route path="/Proposal" element={<Proposal />} />
          <Route path="/UsersList" element={<UsersList />} />
          <Route path="/SendMessage/:id" element={<SendMessage />} />
          <Route path="/SavedJobs" element={<SavedJobs />} />
          <Route path="/MyProposals" element={< MyProposals />} />
          <Route path="/" element={<Login />} />
          <Route path="/SignUp" element={<SignUp />} />
          <Route path="/UpdateUser/:id" element={<UpdateUser />} />
          <Route path="/AHome" element={<AHome />} />
          <Route path="/ProposalsToAccept" element={<ProposalsToAccept />} />
          <Route path="/MyMessages" element={<MyMessages />} />
         
        
         
        </Routes>
      </AuthProvider>
    </Router>
  );
}
export default App;
