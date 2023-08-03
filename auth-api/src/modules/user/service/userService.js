import UserRepository from "../repository/userRepository.js";
import * as httpStatus from '../../../config/constants/httpStatus.js';
import UserException from "../exception/UserException.js";
import bcrypt from 'bcrypt';
import User from "../model/User.js";
import jwt from 'jsonwebtoken';
import * as secret from "../../../config/constants/secret.js";

class UserService {

    async findByEmail(req) {

        try {
            const { email } = req.params;
            const { authUser } = req;
            this.validarDadosRequisicao(email);
            let user = await UserRepository.findByEmail(email);
            this.validateUserNotFound(user);
            this.validateAuthenticatedUser(user, auth);
            return {
                status: httpStatus.SUCCESS,
                user: {
                    id: user.id,
                    name: user.name,
                    email: user.email
                }
            }
        } catch(err) {
            return {
                status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: err.status,
            }
        }

    }

    validarDadosRequisicao(email) {
      if (!email) {
        throw new Error("User email not informed");
      }
    }

    validateUserNotFound(user) {
        if (!user) {
            throw new Error("User not found");
        }
    }

    async getAccessToken() {
        try {
            const {email, password } = req.body;   
            this.validateAccessTokenData(email, password);
            let user = await UserRepository.findByEmail(email);
            this.validateUserNotFound(user);
            await this.validatePassword(password, user.password);
            const authUser = {id: user.id, name: user.name, email: user.email };
            const accessToken = jwt.sign({authUser}, secret.apiSecret, {expiresIn: '10d'},);  
            return {
                status: httpStatus.SUCCESS,
                accessToken,
            }

        } catch (err) {
            return {
                status: err.status ? err.status : httpStatus.INTERNAL_SERVER_ERROR,
                message: err.status,
            }
        }
      

    }

    validateAuthenticatedUser(user, authUser) {
        if (!authUser || user.id !== authUser.id) {
           throw new UserException(
            httpStatus.FORBIDDEN,
            "You cannot see this user data"
           )
        }
    }

    validateAccessTokenData(email, password) {
        if (!email || !password)  {
            throw new UserException(httpStatus.UNAUTHORIZED, "Email and password must be informed.")
        }

    }

    async validatePassword(password, hashPassword) {
      if (!await bcrypt.compare(password, hashPassword)) {
          throw new UserException(httpStatus.UNAUTHORIZED, "Password doesn't match.");
      }
    }

}

export default new UserService();