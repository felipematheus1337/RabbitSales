import jwt from "jsonwebtoken";
import {promisify} from "util";
import * as secrets from "../constants/secret.js";
import * as httpStatus from "../constants/httpStatus.js";
import AccessTokenException from "../auth/AccessTokenException.js";

const emptySpace = " ";

export default async (req, res, next) => { 
    try {
        const { authorization } = req.headers;
        if (!authorization) {
            throw new AccessTokenException(httpStatus.UNAUTHORIZED, "Access token is needed.");
        }
        let accessToken = authorization;
        if (accessToken.toLowerCase().includes(emptySpace)) {
            accessToken = accessToken.split(emptySpace)[1];
        } else {
            accessToken = authorization;
        }
        const decoded = await promisify(jwt.verify)(
            accessToken,
             secrets.API_SECRET
            );
        req.authUser = decoded.authUser;
        return next();    
    } catch(err) {
        return res.status(err.status).json({
            status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
            message: err.status,
        })
    }
}
    