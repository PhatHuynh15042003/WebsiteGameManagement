
import React, { useState } from 'react';
import { MapContainer, TileLayer, Marker, useMapEvents } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import axios from 'axios';

delete L.Icon.Default.prototype._getIconUrl;

L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-icon-2x.png',
  iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-icon.png',
  shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.7.1/images/marker-shadow.png'
});

function LocationMarker({ setLocation }) {
  const [position, setPosition] = useState({
    lat: 10.804602,
    lng: 106.718873,
  });

  useMapEvents({
    click(e) {
      setPosition(e.latlng);
      setLocation(e.latlng);
    },
  });

  return position === null ? null : (
    <Marker position={position}></Marker>
  );
}

const ContactComponent = () => {
  const [location, setLocation] = useState({
    lat: 10.804602,
    lng: 106.718873,
  });

  const [formData, setFormData] = useState({

	email: '',
	subject: '',
	message: ''
});

const handleChange = (e) => {
	setFormData({ ...formData, [e.target.name]: e.target.value });
};

const handleSubmit = (e) => {
	e.preventDefault();
	axios.post('http://localhost:8080/api/contact', null, {
		headers: {
			'Content-Type': 'application/x-www-form-urlencoded'
		}
	})
	.then(response => {
		alert('Message sent successfully!');
	})
	.catch(error => {
		console.error('There was an error sending the message!', error);
	});
};



  return (
    <div>
		<div className=" container p-5" style={{marginLeft:'60px', width:'1150px'}}>
      <MapContainer
        center={[10.804602, 106.718873]}
        zoom={13}
        style={{ height: '400px', width: '100%'}}
        id="map-canvas"
        className="map"
      >
        <TileLayer
          url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        />
        <LocationMarker setLocation={setLocation} />
      </MapContainer>
</div>
	  
      <section className="page-section spad contact-page" style={{marginTop:'0px'}}>
		<div className="container">
			
			<div className="row">
				<div className="col-lg-4 mb-5 mb-lg-0">
					<h4 className="comment-title">Contact us</h4>
					<p style={{fontSize:'12px'}}>Our business hours are 8:00 AM to 6:00 PM Monday-Friday and 9:00 AM to 5:00 AM on weekends.</p>
					<div className="row">
						<div className="col-md-9">
							<ul className="contact-info-list">
								<li><div className="cf-left" style={{fontSize:'12px'}}>Address</div><div className="cf-right" style={{fontSize:'12px'}}>643 Xo Viet Nghe Tinh Ward 26 Binh Thanh District Ho Chi Minh City</div></li>
								<li><div className="cf-left" style={{fontSize:'12px'}}>Phone</div><div className="cf-right" style={{fontSize:'12px'}} >+84 70 6757 167</div></li>
								<li><div className="cf-left" style={{fontSize:'12px'}}>E-mail</div><div className="cf-right" style={{fontSize:'12px'}}>trinhphunghongphuc@gmail.com</div></li>
							</ul>
						</div>
					</div>
					<div className="social-links">
						<img src='https://cdn1.iconfinder.com/data/icons/logos-brands-in-colors/2500/zalo-seeklogo.com-512.png' style={{width:'20px', height:'20px', marginTop:'0px', marginRight:'300px'}}/>
						<a href="#"><i className="fa fa-facebook"></i></a>
						
					</div>
				</div>
				<div className="col-lg-8">
            <div className="contact-form-warp">
                <h4 className="comment-title">Contact to us</h4>
                <form className="comment-form" onSubmit={handleSubmit}>
                    <div className="row">
                        <div className="col-md-6">
                            {/* <input
                                type="text"
                                name="name"
                                placeholder="Name"
                                style={{ width: '250px',  fontSize:'12px', color:'black' }}
                                value={formData.name}
                                onChange={handleChange}
                            /> */}
                        </div>
                        <div className="col-lg-12" style={{ marginTop: '10px', marginLeft: '0px', marginTop: '10px' }}>
                            <input
                                type="email"
                                name="email"
                                placeholder="Email"
                                style={{ width: '250px' ,  fontSize:'12px', color:'black'}}
                                value={formData.email}
                                onChange={handleChange}
                            />
                        </div>
                        <div className="col-lg-12" style={{ marginTop: '10px' }}>
                            <input
                                type="text"
                                name="subject"
                                placeholder="Subject"
                                style={{ width: '20%', fontSize:'12px', color:'black' }}
                                value={formData.subject}
                                onChange={handleChange}
                            />
                            <div></div>
                            <textarea
                                name="message"
                                placeholder="Message"
                                style={{ width: '88%' ,  fontSize:'12px', color:'black'}}
                                value={formData.message}
                                onChange={handleChange}
                            ></textarea>
                            <div></div>
                            <button
                                type="submit"
                                className="btn btn-warning"
                                style={{ color: '#dcdcdc', fontSize: '14px', marginTop: '10px' }}
                            >
                                Send
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
			</div>
		</div>
	</section>
  
    </div>
  );
};

export default ContactComponent;