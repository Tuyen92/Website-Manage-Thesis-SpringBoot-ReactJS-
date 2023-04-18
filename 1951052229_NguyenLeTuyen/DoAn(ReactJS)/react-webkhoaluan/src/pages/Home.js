import React, { Component, useEffect, useState } from 'react'

export default class Home extends Component{

  render(){
  return (
    <div id="carouselExampleControls" className="carousel slide" data-ride="carousel">
    <div className="carousel-inner">
      <div className="carousel-item active">
        <img className="d-block w-100" height={800} width={250} src="picture/Anh1.jpg" alt="First slide" />
      </div>
      <div className="carousel-item">
        <img className="d-block w-100" height={800} width={250} src="picture/Anh2.jpg" alt="Second slide" />
      </div>
      <div className="carousel-item">
        <img className="d-block w-100" height={800} width={250} src="picture/Anh3.jpg" alt="Third slide" />
      </div>
    </div>
    <a className="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
      <span className="carousel-control-prev-icon" aria-hidden="true"></span>
      <span className="sr-only">Previous</span>
    </a>
    <a className="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
      <span className="carousel-control-next-icon" aria-hidden="true"></span>
      <span className="sr-only">Next</span>
    </a>
  </div> 
    
  )}
}
