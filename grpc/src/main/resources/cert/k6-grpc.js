import grpc from 'k6/net/grpc';
import { check } from 'k6';

const client = new grpc.Client();
client.load(
    ['../../proto'],
    'greeter.proto'
);

//  export let options = {
//     insecureSkipTLSVerify: true, // it works with HTTP only unlike gRPC
// };

export default () => {
    client.connect('localhost:6565', {
        plaintext: true
    });

    const data = { name: 'something' };
    const response = client.invoke('helloworld.Greeter/SayHello', data);
    // check(response, {
    //        'status is OK': (r) => r && r.status === grpc.StatusOK,
    // });
    // console.log(JSON.stringify(response.message));
    client.close();
    // sleep(1);
};
