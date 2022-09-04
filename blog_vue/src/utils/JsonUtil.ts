export const JsonUtil = {
  toString: (str: string): string => {
    return JSON.parse(str);
  },
  toJson: (str: string): string => {
    return JSON.stringify(str);
  },
};
