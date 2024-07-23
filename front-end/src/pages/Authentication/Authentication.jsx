import React from "react";
import { Card, Grid } from "@mui/material";
import Login from "./Login";
import Register from "./Register";
const Authentication = () => {
    return (
        <div>
            <Grid container>
                <Grid className="h-screen overflow-hidden" item xs={7}>
                    <img className='h-fill w-full' src='https://www.freelancinggig.com/blog/wp-content/uploads/2019/04/what-is-Social-Media-Cover.png' />
                </Grid>
                <Grid item xs={5}>

                <div className="px-20 flex flex-col justify-center h-full">

                <Card className="card p-8">

                    <div className="flex flex-col items-center mb-5 space-y-1">
                    <h1 className="log">Social Network</h1>
                    <p className="text-center text-sm w-[70&]">Connecting Lives, Sharing your story</p>
                    </div>

                   {<Login/>}
                   {/* {<Register/>} */}
                </Card>

                </div>

                </Grid>
            </Grid>
        </div>
    )
}

export default Authentication