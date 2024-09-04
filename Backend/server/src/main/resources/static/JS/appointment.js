const viewAppointmentModal = document.getElementById("view_modal");
const baseURL = "http://localhost:3001";

// options with default values
const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    setTimeout(() => {
      AppointmentModal.classList.add("scale-100");
    }, 50);
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

// instance options object
const instanceOptions = {
  id: "view_modal",
  override: true,
};

const AppointmentModal = new Modal(
  viewAppointmentModal,
  options,
  instanceOptions
);

function openAppointmentModal() {
  AppointmentModal.show();
}

function closeAppointmentModal() {
  AppointmentModal.hide();
}

async function loaddata(id, docid) {
  console.log(id);
  try {
    const data = await (await fetch(`${baseURL}/api/patient/${id}`)).json();
    const Docdata = await (await fetch(`${baseURL}/doctors/${docid}`)).json();
    

    const DocnameElement = document.querySelector("#doc_name");
    const specialization = document.querySelector("#doc_specialization");

    if (DocnameElement) DocnameElement.innerHTML = Docdata.name || "N/A";
    if (specialization) specialization.innerHTML = Docdata.specialization || "N/A";


    const nameElement = document.querySelector("#user_name");
    const phoneElement = document.querySelector("#user_phone");
    const emergencyContactElement = document.querySelector(
      "#user_emergency_contact"
    );
    const ageElement = document.querySelector("#user_age");
    const genderElement = document.querySelector("#user_gender");

    if (nameElement) nameElement.innerHTML = data.name || "N/A";
    if (phoneElement) phoneElement.innerHTML = data.phoneNumber || "N/A";
    if (emergencyContactElement)
      emergencyContactElement.innerHTML = data.ephoneNumber || "N/A";
    if (ageElement) ageElement.innerHTML = data.age || "N/A";
    if (genderElement) genderElement.innerHTML = data.gender || "N/A";


    openAppointmentModal();

  } catch (error) {
    console.log("Error: ", error);
  }
}

async function deleteAppointment(id) {
  Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    confirmButtonColor: "#3085d6",
    cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!"
  }).then((result) => {
    if (result.isConfirmed) {
      Swal.fire({
        title: "Deleted!",
        text: "Appointment has been deleted.",
        icon: "success",
    });

      // Add a delay before redirecting
      setTimeout(() => {
        const url = `${baseURL}/user/appointments/delete/` + id;
        window.location.replace(url);
      }, 1000); // 2000ms = 2 seconds

    }
  });
}





