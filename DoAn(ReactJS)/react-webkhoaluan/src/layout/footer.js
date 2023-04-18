import React from 'react';
import {
  MDBFooter,
  MDBContainer,
  MDBCol,
  MDBRow
} from 'mdb-react-ui-kit';

export default function App() {
  return (
    <div className='container pt-5 border-bottom'>
      <MDBFooter bgColor='light' className='text-center text-lg-left mt-4 mb-0'>
        <MDBContainer className='p-4'>
          <MDBRow>
            <MDBCol lg='6' md='12' className='mb-4 mb-md-0'>
              <h5 className='text-uppercase'>Trần Điền Long</h5>

              <p>
                MSSV: 1951052106
              </p>
            </MDBCol>

            <MDBCol lg='6' md='12' className='mb-4 mb-md-0'>
              <h5 className='text-uppercase'>Nguyễn Lê Tuyên</h5>

              <p>
                MSSV: 1951052229
              </p>
            </MDBCol>
          </MDBRow>
        </MDBContainer>

        <div className='text-center p-3' style={{ backgroundColor: 'rgba(0, 0, 0, 0.2)' }}>
          &copy; {new Date().getFullYear()} Website quản lý khóa luận
        </div>
      </MDBFooter>
    </div>
  );
}