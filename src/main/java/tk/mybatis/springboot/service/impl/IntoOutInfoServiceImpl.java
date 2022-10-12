package tk.mybatis.springboot.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.springboot.mapper.IntoOutInfoMapper;
import tk.mybatis.springboot.model.bo.IntoOutInfoBO;
import tk.mybatis.springboot.model.dto.IntoOutInfoDTO;
import tk.mybatis.springboot.model.dto.IntoOutInfoDTO;
import tk.mybatis.springboot.model.entity.IntoOutInfo;
import tk.mybatis.springboot.service.IntoOutInfoService;
import tk.mybatis.springboot.service.IntoOutInfoService;

import java.util.List;

@Service
public class IntoOutInfoServiceImpl implements IntoOutInfoService {
    @Autowired
    private IntoOutInfoMapper intoOutInfoMapper;

    @Override
    public List<IntoOutInfoBO> getAll(IntoOutInfoDTO intoOutInfoDTO) {
        if (intoOutInfoDTO.getStart() != null && intoOutInfoDTO.getPageSize() != null) {
            PageHelper.startPage(intoOutInfoDTO.getStart(), intoOutInfoDTO.getPageSize());
        }
        return intoOutInfoMapper.findPageWithResult(intoOutInfoDTO);
    }
}
