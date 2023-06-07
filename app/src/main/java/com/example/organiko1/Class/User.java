package com.example.organiko1.Class;

public class User {

        private  Long id ;
        private String userName;
        private String email;
        private String psswrd;
        private String nombre_completo;

        public Long getId() {
                return id;
        }

        public void setId(Long id) {
                this.id = id;
        }

        public String getUserName() {
                return userName;
        }

        public void setUserName(String userName) {
                this.userName = userName;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getPsswrd() {
                return psswrd;
        }

        public void setPsswrd(String psswrd) {
                this.psswrd = psswrd;
        }

        public String getNombre_completo() {
                return nombre_completo;
        }

        public void setNombre_completo(String nombre_completo) {
                this.nombre_completo = nombre_completo;
        }
}
