package pt.memplus.web.model.repository;

import pt.memplus.web.model.repository.memory.DoctorRepository;
import pt.memplus.web.model.repository.memory.EventRepository;
import pt.memplus.web.model.repository.memory.PacientRepository;
import pt.memplus.web.model.repository.memory.RelativeRepository;
import pt.memplus.web.model.repository.memory.TechnitianRepository;
import pt.memplus.web.models.Doctor;
import pt.memplus.web.models.Event;
import pt.memplus.web.models.Patient;
import pt.memplus.web.models.Relative;
import pt.memplus.web.models.Technician;

public class DataRepositoryLocator {
	private static final IRepository<Patient> pacientRepository = new PacientRepository();
	private static final IRepository<Doctor> doctorRepository = new DoctorRepository();
	private static final IRepository<Technician> technitianRepository = new TechnitianRepository();
	private static final IRepository<Relative> relativeRepository = new RelativeRepository();
	private static final IRepository<Event> eventRepository = new EventRepository();
	
	
	public static IRepository<Patient> getPacientRepository() {
		return pacientRepository;
	}
	public static IRepository<Doctor> getDoctorRepository() {
		return doctorRepository;
	}
	public static IRepository<Technician> getTechnitianRepository() {
		return technitianRepository;
	}
	public static IRepository<Relative> getRelativeRepository() {
		return relativeRepository;
	}
	public static IRepository<Event> getEventRepository(){
		return eventRepository;
	}

}
