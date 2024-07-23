import { TextField, Button, RadioGroup,Radio,FormControlLabel } from "@mui/material";
import { ErrorMessage, Form, Field, Formik } from "formik";
import React, { useState } from "react";
import * as Yup from "yup";
import { registerUserAction } from "../../Redux/Auth/auth.action";
import { useDispatch } from "react-redux";

const initialValues = { firstName:"", lastName:"",email: "", password: "", gender:"" };
const validationSchema = {
    email: Yup.string().email("Invalid email").required("Email is required"),
    password: Yup.string().min(6, "Password must be at least 6 characters").required("Password is required")
};

const Register = () => {
    const [gender, setGender] = useState("");
    const dispatch = useDispatch();

    const handleSubmit = (values) => {
        values.gender = gender;
        console.log("handele submit", values)
        dispatch(registerUserAction({data:values}))
    }
    const handleChange = (event) => {
        setGender(event.target.value)
    }
    return (
        <>
            <Formik
                onSubmit={handleSubmit}
                // validationSchema={validationSchema} 
                initialValues={initialValues}
            >
                <Form className="space-y-5">

                    <div className="space-y-5">
                        <div>
                            <Field as={TextField} name="firstName" placeholder="First Name" type="firstName" variant="outlined" fullWidth />
                            <ErrorMessage name="firstName" component="div" className="text-red-500" />
                        </div>
                        <div>
                            <Field as={TextField} name="lastName" placeholder="Last Name" type="lastName" variant="outlined" fullWidth />
                            <ErrorMessage name="lastName" component="div" className="text-red-500" />
                        </div>
                        <div>
                            <Field as={TextField} name="email" placeholder="Email" type="email" variant="outlined" fullWidth />
                            <ErrorMessage name="email" component="div" className="text-red-500" />
                        </div>
                        <div>
                            <Field as={TextField} name="password" placeholder="password" type="password" variant="outlined" fullWidth />
                            <ErrorMessage name="password" component="div" className="text-red-500" />
                        </div>

                        <div>
                            <RadioGroup onChange={handleChange}
                                row 
                                aria-labelledby="gender"
                                defaultValue="female"
                                name="gender"
                            >
                                <FormControlLabel value="female" control={<Radio />} label="Female" />
                                <FormControlLabel value="male" control={<Radio />} label="Male" />
                                <ErrorMessage name="gender" component="div" className="text-red-500" />

                            </RadioGroup>
                        </div>

                    </div>
                    <Button sx={{ padding: ".8rem 0rem" }} fullWidth type="submit" variant="contained" color="primary" >Register</Button>

                </Form>
            </Formik>
        </>
    )

}

export default Register;