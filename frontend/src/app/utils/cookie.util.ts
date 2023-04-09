export class CookieUtil {
  getCookie(name: string): string | undefined {
    const value: string = `; ${document.cookie}`;
    const parts: string[] | undefined = value?.split(`; ${name}=`);
    if (parts && parts.length === 2) {
      return (parts as any).pop().split(';').shift();
    }
    return undefined;
  }
}
